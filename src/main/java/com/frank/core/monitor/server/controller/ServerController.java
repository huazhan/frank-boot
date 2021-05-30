package com.frank.core.monitor.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frank.core.monitor.server.entity.Server;
import com.frank.core.monitor.server.service.ServerService;
import com.frank.framework.common.JsonResult;

@RestController
@RequestMapping("/server")
public class ServerController {
	
	@Autowired
	private ServerService serverService;
	
	@RequestMapping("/getServer")
	public JsonResult getServer(){
		Server server = serverService.getServer();
		return JsonResult.success(server);
	}

}
