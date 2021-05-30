package com.frank.core.monitor.server.entity;

/**
 * 硬盘信息
 * @ClassName HardDisk
 * @Author chenhuazhan
 * @Date: 2021年5月26日 下午5:46:26 
 * @Description: TODO
 * @Version 1.0
 */
public class HardDisk {
	
    private String dirName; // 盘符路径

    private String sysTypeName; // 盘符类型

    private String typeName; // 文件类型

    private String total; // 总大小

    private String free; // 剩余大小

    private String used; // 已经使用量

    private double usage; // 资源的使用率

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getSysTypeName() {
		return sysTypeName;
	}

	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public double getUsage() {
		return usage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}
    
    

}
