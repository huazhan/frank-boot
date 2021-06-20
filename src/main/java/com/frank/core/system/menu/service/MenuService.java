package com.frank.core.system.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.frank.core.system.menu.entity.Menu;
import com.frank.core.system.menu.entity.MenuTree;
import com.frank.core.system.menu.mapper.MenuMapper;
import com.frank.framework.util.UserUtils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;

@Service
public class MenuService {
	
	@Autowired
	private MenuMapper menuMapper;

	public Menu selectById(long id) {
		return menuMapper.selectById(id);
	}

	public List<Menu> list(Menu menu) {
		return menuMapper.list(menu);
	}

	public void insert(Menu menu) {
		menu.setCreateBy(UserUtils.getCurrentUsername());
		menu.setCreateTime(DateUtil.date());
		menu.setUpdateBy(UserUtils.getCurrentUsername());
		menu.setUpdateTime(DateUtil.date());
		menuMapper.insert(menu);
	}

	public void update(Menu menu) {
		menu.setUpdateBy(UserUtils.getCurrentUsername());
		menu.setUpdateTime(DateUtil.date());
		menuMapper.update(menu);
	}

	public void delete(long id) {
		menuMapper.delete(id);
	}

	public void batchDelete(List<Long> idList) {
		menuMapper.batchDelete(idList);
	}

	public List<MenuTree> selectMenuTree() {

		String currentUsername = UserUtils.getCurrentUsername();

		List<MenuTree> result = new ArrayList<>();
		List<MenuTree> list = menuMapper.selectMenuTree(currentUsername);
		
		for (MenuTree adModule : list) {
            // 根节点
            if ("0".equals(adModule.getParentId())) {
            	result.add(getChildrens(adModule, list));
            }
        }

		return result;
		
	}
	
	 private MenuTree getChildrens(MenuTree module, List<MenuTree> list) {
	        List<MenuTree> childNodes = new ArrayList<>();
	        for (MenuTree node : list) {
	            if (node.getParentId().equals(module.getId())) {
	                childNodes.add(getChildrens(node, list));
	            }
	        }
	        module.setChildren(childNodes);
	        return module;
	    }

	public List<MenuTree> selectMenuTreeForRole() {
		
		List<Menu> list = menuMapper.list(null);
		
		// 构建node列表
		//List<DeptTree> nodeList = CollUtil.newArrayList();
		List<TreeNode<String>> nodeList = CollUtil.newArrayList();
		for (Menu menu : list) {
			nodeList.add(new TreeNode<>(String.valueOf(menu.getId()), String.valueOf(menu.getParentId()), menu.getName(), menu.getSort()));
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

		List<MenuTree> menuTreeList = JSON.parseArray(JSON.toJSONString(treeNodes), MenuTree.class);
		
		return menuTreeList;
	}

}
