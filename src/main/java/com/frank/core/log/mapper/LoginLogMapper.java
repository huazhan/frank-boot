package com.frank.core.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.core.log.entity.LoginLog;

public interface LoginLogMapper {

	void insert(LoginLog loginLog);

	void updateBySessionId(@Param("sessionId") String sessionId);

	List<LoginLog> list(LoginLog loginLog);

}
