package com.frank.framework.security;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.frank.core.system.user.mapper.UserMapper;
import com.frank.framework.exception.BusinessException;
import com.frank.framework.security.entity.MyUserDetails;
import com.frank.framework.security.entity.RoleAndPerms;

import cn.hutool.core.collection.CollUtil;

/**
 * 自定义登录认证
 * @author Frank
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		MyUserDetails sysUser = userMapper.seleteByUserName(username);
		
		if (sysUser == null) {
			throw new BusinessException("账号不存在");
		}
		
		if ("0".equals(sysUser.getStatus())) {
			throw new BusinessException("账号已禁用");
		}
		
		// 权限
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		List<RoleAndPerms> roleAndPermsList = userMapper.selectRoleAndPermsListByUserName(username);
		if(CollUtil.isNotEmpty(roleAndPermsList)) {
			
			// 拥有的角色。使用方式如：在controller相应方法中加上注解：@PreAuthorize("hasRole('admin')")
			Set<String> roleKeySet = roleAndPermsList.stream().map(RoleAndPerms::getRoleKey).collect(Collectors.toSet());
			for (String roleKey : roleKeySet) {
				authorities.add(new SimpleGrantedAuthority("ROLE_"+roleKey));
			}
			
			// 拥有的权限。使用方式如：在controller相应方法中加上注解：@PreAuthorize("hasAuthority('sys:user:delete')")
			Set<String> permsSet = roleAndPermsList.stream().map(RoleAndPerms::getPerms).filter(perms -> StringUtils.isNotBlank(perms)).collect(Collectors.toSet());
			String permsString = StringUtils.join(permsSet, ",");
			List<GrantedAuthority> commaSeparatedStringToAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(permsString);
			authorities.addAll(commaSeparatedStringToAuthorityList);
			
			log.info("用户【"+username+"】拥有的角色："+JSON.toJSONString(roleKeySet));
			log.info("用户【"+username+"】拥有的权限："+JSON.toJSONString(permsSet));
			
		}
		
		//String password = new BCryptPasswordEncoder().encode("123456");
		//String password = "$2a$10$GRPaS5mfqp1zwXrizfhAueZJhuIjOn.ROSAb3Slp.8/b4ApxhJadm";
		String password = sysUser.getPassword();
		
		/**
		 * String username 用户名, 
		 * String password 密码, 
		 * boolean enabled 账号是否启用 	true-启用，false-未启用。若false则抛出【User is disabled】
		   boolean accountNonExpired 账号是否尚未过期	true-未过期，false-已过期,。若false则抛出【User account has expired】
		   boolean credentialsNonExpired 凭证是否尚未过期	true-未过期，false-已过期。若false则抛出【User credentials have expired】
		   boolean accountNonLocked	账号是否未锁定	 true-未锁，false-锁定。若false则抛出【User account is locked】
		   Collection<? extends GrantedAuthority> authorities 
		 */
		return new User(username, password, authorities);
		// return new User(username, password, true, true, true, true, authorities);
		
	}

}
