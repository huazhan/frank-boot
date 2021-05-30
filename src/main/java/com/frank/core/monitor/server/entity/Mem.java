package com.frank.core.monitor.server.entity;

import cn.hutool.core.util.NumberUtil;

public class Mem {
	
    private double total; // 内存总量

    private double used; // 已用内存

    private double free; // 剩余内存
    
    private double freeRate; // 空闲率

    public double getTotal() {
        return NumberUtil.div(total, (1024 * 1024 * 1024), 2);
    }

    public double getUsed() {
        return NumberUtil.div(used, (1024 * 1024 * 1024), 2);
    }


    public double getFree() {
        return NumberUtil.div(free, (1024 * 1024 * 1024), 2);
    }

    public double getUsage() {
        return NumberUtil.mul(NumberUtil.div(used, total, 4), 100);
    }
    
    public double getFreeRate() {
		return NumberUtil.sub(100D, getUsage());
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public void setFreeRate(double freeRate) {
		this.freeRate = freeRate;
	}
	
	
    
    
	

	
}
