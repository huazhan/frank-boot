package com.frank.core.system.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frank.core.system.role.entity.Role;
import com.frank.core.system.role.entity.RoleMenu;
import com.frank.core.system.role.mapper.RoleMapper;
import com.frank.framework.security.util.SecurityUtil;
import com.frank.framework.util.UserUtils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

@Service
public class RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private SecurityUtil securityUtil;

	public Role selectById(long id) {
		Role role = roleMapper.selectById(id);
		return role;
	}

	public List<Role> list(Role role) {
		List<Role> list = roleMapper.list(role);
		return list;
	}

	@Transactional(value = "transactionManager")
	public void insert(Role role) {
		
		role.setCreateBy(UserUtils.getCurrentUsername());
		role.setCreateTime(DateUtil.date());
		role.setUpdateBy(UserUtils.getCurrentUsername());
		role.setUpdateTime(DateUtil.date());
		
		roleMapper.insert(role);
		// mapper.xml的insert中使用标签 useGeneratedKeys="true"  keyProperty="id" ，会在插入成功后自动将id值返回到实体类对应id字段中
		long roleId = role.getId();
	
		long[] menuIds = StrUtil.splitToLong(role.getMenuIds(), ",");
		for (Long menuId : menuIds) {
			RoleMenu roleMenu = new RoleMenu(roleId, menuId);
			roleMapper.insertRoleMenu(roleMenu);
		}
	}

	@Transactional(value = "transactionManager")
	public void update(Role role) {
		
		role.setUpdateBy(UserUtils.getCurrentUsername());
		role.setUpdateTime(DateUtil.date());
		
		roleMapper.update(role);
		
		long roleId = role.getId();
		// 先删除用户与角色的关联信息，再重新绑定
		roleMapper.deleteRoleMenuByRoleId(roleId);
		
		long[] menuIds = StrUtil.splitToLong(role.getMenuIds(), ",");
		for (Long menuId : menuIds) {
			RoleMenu roleMenu = new RoleMenu(roleId, menuId);
			roleMapper.insertRoleMenu(roleMenu);
		}
		
		// 刷新当前在线用户的权限
		securityUtil.refreshGrantedAuthority();
		
	}
	
	public void updateStatus(Role role) {
		role.setUpdateBy(UserUtils.getCurrentUsername());
		role.setUpdateTime(DateUtil.date());
		roleMapper.update(role);
	}

	@Transactional(value = "transactionManager")
	public void delete(long id) {
		roleMapper.delete(id);
		
		roleMapper.deleteRoleMenuByRoleId(id);
		
		roleMapper.deleteUserRoleByRoleId(id);
		
	}

	@Transactional(value = "transactionManager")
	public void batchDelete(List<Long> idList) {
		roleMapper.batchDelete(idList);
		
		for (Long id : idList) {
			roleMapper.deleteRoleMenuByRoleId(id);
			
			roleMapper.deleteUserRoleByRoleId(id);
		}
	}


}
