package com.frank.core.log.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.core.log.entity.LoginLog;
import com.frank.core.log.mapper.LoginLogMapper;
import com.frank.framework.util.AddressUtils;
import com.frank.framework.util.IpUtils;
import com.frank.framework.util.UserUtils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;

/**
 * 登录日志Service
 * @author Frank
 *
 */
@Service
public class LoginLogService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginLogService.class);
	
	@Autowired
	private LoginLogMapper loginLogMapper;
	
	public void insert(HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		String username = UserUtils.getCurrentUsername(); // 操作人
        String ip = IpUtils.getIpAddr(request); // 获取用户的ip
        String address = AddressUtils.getCityByIp(ip);
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        String os = ua.getOs().toString(); // Windows 7
        String browser = ua.getBrowser().toString(); // Chrome
        
        logger.info("sessionId:"+sessionId);
        logger.info("username:"+username);
        logger.info("ip:"+ip);
        logger.info("address:"+address);
        logger.info("os:"+os);
        logger.info("browser:"+browser);
        
        LoginLog loginLog = new LoginLog();
        loginLog.setSessionId(sessionId);
        loginLog.setUsername(username);
        loginLog.setIp(ip);
        loginLog.setAddress(address);
        loginLog.setOs(os);
        loginLog.setBrowser(browser);
        loginLog.setOnline("0"); // 默认在线
        loginLog.setCreateBy(UserUtils.getCurrentUsername());
        loginLog.setCreateTime(DateUtil.date());
        loginLog.setUpdateBy(UserUtils.getCurrentUsername());
        loginLog.setUpdateTime(DateUtil.date());
        
        // 新增登录日志
        loginLogMapper.insert(loginLog);
        
	}

	public void updateBySessionId(String sessionId) {
		loginLogMapper.updateBySessionId(sessionId);
	}

	public List<LoginLog> list(LoginLog loginLog) {
		return loginLogMapper.list(loginLog);
	}

}
