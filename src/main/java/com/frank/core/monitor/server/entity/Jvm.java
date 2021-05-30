package com.frank.core.monitor.server.entity;

import java.lang.management.ManagementFactory;
import java.util.Date;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.system.JavaInfo;
import cn.hutool.system.JavaRuntimeInfo;
import cn.hutool.system.SystemUtil;

public class Jvm {
	
    private double total; // 当前JVM占用的内存总数(M)

    private double max; // JVM最大可用内存总数(M)

    private double free; // JVM空闲内存(M)
    
    private double freeRate; // 空闲率

    private String name; // JVM名称

    private String version; // JDK版本

    private String home; // JDK路径
    
    private Double usage; // 使用率
     
    private String startTime; // JDK启动时间
    
    private String runTime; // JDK运行时间
    
	public double getTotal() {
        return NumberUtil.div(total, (1024 * 1024), 2);
    }

    public double getMax() {
        return NumberUtil.div(max, (1024 * 1024), 2);
    }

    public double getFree() {
        return NumberUtil.div(free, (1024 * 1024), 2);
    }

    public double getUsed() {
        return NumberUtil.div(total - free, (1024 * 1024), 2);
    }
    
	public double getFreeRate() {
		return NumberUtil.mul(NumberUtil.div(free, total, 4), 100);
	}

    /**
     * JDK版本
     */
    public String getVersion() {
    	JavaInfo javaInfo = SystemUtil.getJavaInfo();
        return javaInfo.getVersion();
    }

    /**
     * JDK路径
     */
    public String getHome() {
    	JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();
        return javaRuntimeInfo.getHomeDir();
    }

    public Double getUsage() {
        return NumberUtil.mul(NumberUtil.div(total - free, total, 4), 100);
    }
    /**
     * 	获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    /**
     * JDK启动时间
     */
    public String getStartTime() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        Date date = new Date(time);
        return DateUtil.formatDateTime(date);
    }

    /**
     * JDK运行时间
     */
    public String getRunTime() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        Date date = new Date(time);

        //运行多少分钟
        long runMS = DateUtil.between(date, new Date(), DateUnit.MS);

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

        long day = runMS / nd;
        long hour = runMS % nd / nh;
        long min = runMS % nd % nh / nm;
        
        String str = "";
        
        if (day != 0) {
			str += day + "天";
		}
        if (hour != 0) {
        	str += hour + "小时";
		}
        str += min + "分钟";
        
        return str;
    }

	public void setTotal(double total) {
		this.total = total;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public void setFreeRate(double freeRate) {
		this.freeRate = freeRate;
	}




    
	

}
