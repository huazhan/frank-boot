package com.frank.core.monitor.server.entity;

import java.util.List;

/**
 * 服务器信息
 * @ClassName Server
 * @Author chenhuazhan
 * @Date: 2021年5月26日 下午3:20:51 
 * @Description: TODO
 * @Version 1.0
 */
public class Server {
	
	private Sys sys; // 系统信息
	
	private Cpu cpu; // CPU信息
	
	private Jvm jvm; // JVM信息
	
	private Mem mem; // 内存信息
	
	private List<HardDisk> hardDisk;  // 硬盘信息

	public Cpu getCpu() {
		return cpu;
	}

	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	public Jvm getJvm() {
		return jvm;
	}

	public void setJvm(Jvm jvm) {
		this.jvm = jvm;
	}

	public Mem getMem() {
		return mem;
	}

	public void setMem(Mem mem) {
		this.mem = mem;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public List<HardDisk> getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(List<HardDisk> hardDisk) {
		this.hardDisk = hardDisk;
	}

}
