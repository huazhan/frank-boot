package com.frank.core.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.core.log.entity.OperLog;

public interface OperLogMapper {

	void insert(OperLog sysLog);

	List<OperLog> list(OperLog operLog);

	OperLog selectById(@Param("id") long id);

}
