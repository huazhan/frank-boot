package com.frank.core.log.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.core.log.entity.OperLog;
import com.frank.core.log.mapper.OperLogMapper;

@Service
public class OperLogService {
	
	@Autowired
	private OperLogMapper operLogMapper;

	public List<OperLog> list(OperLog operLog) {
		return operLogMapper.list(operLog);
	}

	public OperLog selectById(long id) {
		return operLogMapper.selectById(id);
	}

}
