package com.frank.core.monitor.server.entity;


public class Cpu {
	
    private int cpuNum; // 物理核心数
    
    private int processNum; // 线程数

    private double total; // CPU总的使用率

    private double sys; // CPU系统使用率

    private double used; // CPU用户使用率

    private double wait; // CPU当前等待率

    private double free; // CPU当前空闲率
    
    private String cpuModel; // CPU型号信息
    
	public int getCpuNum() {
		return cpuNum;
	}

	public void setCpuNum(int cpuNum) {
		this.cpuNum = cpuNum;
	}
	
	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getSys() {
		return sys;
	}

	public void setSys(double sys) {
		this.sys = sys;
	}

	public double getUsed() {
		return used;
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public double getWait() {
		return wait;
	}

	public void setWait(double wait) {
		this.wait = wait;
	}

	public double getFree() {
		return free;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public String getCpuModel() {
		return cpuModel;
	}

	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

    
   

}
