package com.frank.core.system.dept.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.frank.core.system.dept.entity.Dept;
import com.frank.core.system.dept.entity.DeptTree;
import com.frank.core.system.dept.mapper.DeptMapper;
import com.frank.core.system.user.entity.User;
import com.frank.core.system.user.mapper.UserMapper;
import com.frank.framework.exception.BusinessException;
import com.frank.framework.util.UserUtils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;

@Service
public class DeptService {
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private UserMapper userMapper;

	public Dept selectById(long id) {
		return deptMapper.selectById(id);
	}
	
	public List<Dept> selectChildrenListById(long id) {
		return deptMapper.selectChildrenListById(id);
	}

	public List<Dept> list(Dept dept) {
		return deptMapper.list(dept);
	}

	public void insert(Dept dept) {
		dept.setCreateBy(UserUtils.getCurrentUsername());
		dept.setCreateTime(DateUtil.date());
		dept.setUpdateBy(UserUtils.getCurrentUsername());
		dept.setUpdateTime(DateUtil.date());
		// 查询当前部门表数据中最大的id值
		long maxId = deptMapper.selectMaxId();
		long id = dept.getId();
		long nextId = maxId + 1L;
		Long parentId = dept.getParentId() == 0L ? id : dept.getParentId();
		Dept parentDept = deptMapper.selectById(parentId); // 所选择的父部门
		String ids = parentDept.getIds() + "," + nextId;
		dept.setId(nextId);
		dept.setIds(ids);
		dept.setParentId(parentId);
		deptMapper.insert(dept);
	}

	@Transactional(value = "transactionManager")
	public void update(Dept dept) {
		dept.setUpdateBy(UserUtils.getCurrentUsername());
		dept.setUpdateTime(DateUtil.date());
		long id = dept.getId();
		long parentId = dept.getParentId() == 0L ? id : dept.getParentId();
		Dept parentDept = deptMapper.selectById(parentId); // 所选择的父部门
		
		String ids = parentDept.getIds();
		List<String> idsSplitTrim = StrUtil.splitTrim(ids, ",");
		if (!idsSplitTrim.contains(String.valueOf(id))) {
			ids = ids + "," + id;
		}
		dept.setIds(ids);
		deptMapper.update(dept);
		
		// 如果是启用/禁用部门，则父部门下的子部门也一起启用/禁用
		if (dept != null && StringUtils.isNotBlank(dept.getStatus())) {
			deptMapper.updateStatusByParentId(parentDept);
		}
	}
	
	public void updateStatusByParentId(Dept dept) {
		dept.setUpdateBy(UserUtils.getCurrentUsername());
		dept.setUpdateTime(DateUtil.date());
		if (dept != null && 0 == dept.getParentId() && "0".equals(dept.getStatus())) {
			throw new BusinessException("总部不能禁用");
		}
		// 如果是启用/禁用部门，则父部门下的子部门也一起启用/禁用
		if (dept != null && StringUtils.isNotBlank(dept.getStatus())) {
			deptMapper.updateStatusByParentId(dept);
		}
	}

	public void delete(long id) {
		
		// 根据deptId查询该部门及其子部门下的所有用户列表
		List<User> userList = userMapper.selectByDeptIdIncludeSub(id);
		//  判断该部门下是否有用户绑定，有则不能删除
		if (CollUtil.isNotEmpty(userList)) {
			throw new BusinessException("该部门或其子部门下存在绑定用户，无法删除");
		}
		deptMapper.delete(id);
	}

	public List<DeptTree> selectDeptTree() {
		List<Dept> list = deptMapper.list(null);
		
		// 构建node列表
		//List<DeptTree> nodeList = CollUtil.newArrayList();
		List<TreeNode<String>> nodeList = CollUtil.newArrayList();
		for (Dept dept : list) {
			nodeList.add(new TreeNode<>(String.valueOf(dept.getId()), String.valueOf(dept.getParentId()), dept.getName(), dept.getId()));
		}
		
		//配置
		TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
		// 自定义属性名 都要默认值的
		treeNodeConfig.setIdKey("id");
		// 最大递归深度
		treeNodeConfig.setDeep(5);

		//转换器
		List<Tree<String>> treeNodes = TreeUtil.build(nodeList, "0", treeNodeConfig,
		        (treeNode, tree) -> {
		            tree.setId(treeNode.getId());
		            tree.setParentId(treeNode.getParentId());
		            tree.setWeight(treeNode.getWeight());
		            tree.setName(treeNode.getName());
		            // 扩展属性 ...
		            tree.putExtra("title", treeNode.getName());
		            tree.putExtra("checkArr", "0");
		        });

		List<DeptTree> deptTreeList = JSON.parseArray(JSON.toJSONString(treeNodes), DeptTree.class);
		
		return deptTreeList;
	}

}
