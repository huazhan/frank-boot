package com.frank.core.system.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frank.core.system.user.entity.User;
import com.frank.core.system.user.entity.UserRole;
import com.frank.core.system.user.mapper.UserMapper;
import com.frank.framework.security.util.SecurityUtil;

import cn.hutool.core.util.StrUtil;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SecurityUtil securityUtil;

	public List<User> list(User user) {
		return userMapper.list(user);
	}

	@Transactional
	public void insert(User user) {
		
		// 密码加密
		String password = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(password);
		
		// 新增用户
		userMapper.insert(user); 
		
		// 绑定用户与角色的关联信息
		long[] roleIds = StrUtil.splitToLong(user.getRoleIds(), ",");
		for (long roleId : roleIds) {
			UserRole userRole = new UserRole(user.getId(),roleId);
			userMapper.insertUserRole(userRole);
		}
		
	}

	@Transactional
	public void update(User user) {
		// 更新用户信息
		userMapper.update(user);
		
		// 先删除用户与角色的关联信息，再重新绑定
		userMapper.deleteUserRoleByUserId(user.getId());
		
		long[] roleIds = StrUtil.splitToLong(user.getRoleIds(), ",");
		for (long roleId : roleIds) {
			UserRole userRole = new UserRole(user.getId(),roleId);
			userMapper.insertUserRole(userRole);
		}
		
		// 刷新当前在线用户的权限
		securityUtil.refreshGrantedAuthority();
	}
	
	@Transactional
	public void updateStatus(User user) {
		// 更新用户信息
		userMapper.update(user);
	}

	@Transactional
	public void delete(long id) {
		// 删除用户信息
		userMapper.delete(id);
		// 同时删除用户与角色的关联信息
		userMapper.deleteUserRoleByUserId(id);
	}

	public User selectById(long id) {
		return userMapper.selectById(id);
	}

	@Transactional
	public void batchDelete(List<Long> idList) {
		// 删除用户信息
		userMapper.batchDelete(idList);
		for (long id : idList) {
			// 同时删除用户与角色的关联信息
			userMapper.deleteUserRoleByUserId(id);
		}
	}

	

}
