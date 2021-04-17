package com.frank.framework.security.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.frank.core.system.user.mapper.UserMapper;
import com.frank.framework.security.entity.RoleAndPerms;
import com.frank.framework.util.UserUtils;

import cn.hutool.core.collection.CollUtil;

@Component
public class SecurityUtil {

	private static final Logger log = LoggerFactory.getLogger(SecurityUtil.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SessionRegistry sessionRegistry;

	@Autowired
	private SessionManageUtil sessionManageUtil;

	/**
	 * 刷新当前在线用户的权限
	 */
	public void refreshGrantedAuthority() {
		System.out.println("当前登录人："+UserUtils.getCurrentUsername());
		List<GrantedAuthority> authorities = new ArrayList<>();

		List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		for (Object object : allPrincipals) {
			List<SessionInformation> allSessions = sessionRegistry.getAllSessions(object, true);
			for (SessionInformation sessionInformation : allSessions) {
				String sessionId = sessionInformation.getSessionId();
				HttpSession httpSession = sessionManageUtil.getSession(sessionId);
				SecurityContext securityContext = (SecurityContext) httpSession.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
				Authentication authentication = securityContext.getAuthentication();
				Object principal = authentication.getPrincipal();
				if (principal instanceof User) {
					User user = (User) principal;
					String username = user.getUsername();

					List<RoleAndPerms> roleAndPermsList = userMapper.selectRoleAndPermsListByUserName(username);
					if (CollUtil.isNotEmpty(roleAndPermsList)) {
						// 拥有的角色。使用方式如：在controller相应方法中加上注解：@PreAuthorize("hasRole('admin')")
						Set<String> roleKeySet = roleAndPermsList.stream().map(RoleAndPerms::getRoleKey)
								.collect(Collectors.toSet());
						for (String roleKey : roleKeySet) {
							authorities.add(new SimpleGrantedAuthority("ROLE_" + roleKey));
						}

						// 拥有的权限。使用方式如：在controller相应方法中加上注解：@PreAuthorize("hasAuthority('sys:user:delete')")
						Set<String> permsSet = roleAndPermsList.stream().map(RoleAndPerms::getPerms)
								.filter(perms -> StringUtils.isNotBlank(perms)).collect(Collectors.toSet());
						String permsString = StringUtils.join(permsSet, ",");
						List<GrantedAuthority> commaSeparatedStringToAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(permsString);

						authorities.addAll(commaSeparatedStringToAuthorityList);

						log.info("刷新用户【" + username + "】拥有的角色：" + JSON.toJSONString(roleKeySet));
						log.info("刷新用户【" + username + "】拥有的权限：" + JSON.toJSONString(permsSet));
					}

					Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
							authentication.getCredentials(), authorities);

					securityContext.setAuthentication(newAuth);
					
				}
			}
		}
	}

}
