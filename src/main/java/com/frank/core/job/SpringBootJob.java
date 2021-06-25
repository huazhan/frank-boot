package com.frank.core.job;

import com.alibaba.fastjson.JSON;
import com.frank.core.log.entity.LoginLog;
import com.frank.core.log.mapper.LoginLogMapper;
import com.frank.framework.security.util.SessionManageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Configurable
@EnableScheduling
@Component
public class SpringBootJob {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootJob.class);

    @Autowired
    private SessionManageUtil sessionManageUtil;

    @Autowired
    private LoginLogMapper loginLogMapper;


    /**
     * 由于非正常操作停止应用程序，导致系统停了，但在线用户状态无法更新为离线状态，下次启动系统时显示在线，其实已经离线了。
     * 所以需要定时检查用户SessionId是否在线。不在线的更新状态。
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void sessionJob(){

        logger.info("执行定时任务检查用户Session是否在线");

        Set<String> sessionIdSet = sessionManageUtil.getSessionIdSet();

        logger.info("sessionIdSet:{}", JSON.toJSONString(sessionIdSet));

        // 查询在线用户的sessionId
        LoginLog loginLog = new LoginLog();
        loginLog.setOnline("0");
        List<LoginLog> list = loginLogMapper.list(loginLog);
        for (LoginLog log : list) {
            String sessionId = log.getSessionId();
            boolean contains = sessionIdSet.contains(sessionId);
            // 如果sessionIdSet不包含在线用户sessionId，则更新为离线
            if (!contains) {
                loginLogMapper.updateBySessionId(sessionId);
            }
        }
    }
}
