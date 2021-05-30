package com.frank.core.monitor.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.frank.core.monitor.server.entity.Cpu;
import com.frank.core.monitor.server.entity.HardDisk;
import com.frank.core.monitor.server.entity.Jvm;
import com.frank.core.monitor.server.entity.Mem;
import com.frank.core.monitor.server.entity.Server;
import com.frank.core.monitor.server.entity.Sys;
import com.frank.framework.util.ConvertUtil;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.system.HostInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import cn.hutool.system.UserInfo;
import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

@Service
public class ServerService {

	public Server getServer() {
		
		Server server = new Server();
		server.setCpu(initCpu());
		server.setJvm(initJvm());
		server.setMem(initMem());
		server.setSys(initSys());
		server.setHardDisk(initHardDiskList());
		
		return server;
	}
	
	private List<HardDisk> initHardDiskList() {
		
		List<HardDisk> hardDiskList = new ArrayList<>();
		
		SystemInfo si = new SystemInfo();
		OperatingSystem os = si.getOperatingSystem();
		FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fileStores = fileSystem.getFileStores();
        for (OSFileStore fs : fileStores) {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            HardDisk hardDisk = new HardDisk();
            hardDisk.setDirName(fs.getMount());
            hardDisk.setSysTypeName(fs.getType());
            hardDisk.setTypeName(fs.getName());
            hardDisk.setTotal(ConvertUtil.convertCapacity(total));
            hardDisk.setFree(ConvertUtil.convertCapacity(free));
            hardDisk.setUsed(ConvertUtil.convertCapacity(used));
            hardDisk.setUsage(NumberUtil.mul(NumberUtil.div(used, total, 4), 100));
            hardDiskList.add(hardDisk);
        }
		return hardDiskList;
	}

	private Sys initSys() {
		Sys sys = new Sys();
		OsInfo osInfo = SystemUtil.getOsInfo();
		HostInfo hostInfo = SystemUtil.getHostInfo();
		UserInfo userInfo = SystemUtil.getUserInfo();
        sys.setOsName(osInfo.getName());
        sys.setOsArch(osInfo.getArch());
        sys.setUserDir(userInfo.getCurrentDir());
        sys.setComputerName(hostInfo.getName());
        sys.setComputerIp(hostInfo.getAddress());
		return sys;
	}

	private Mem initMem() {
		Mem mem = new Mem();
		HardwareAbstractionLayer hardware = OshiUtil.getHardware();
		GlobalMemory memory = hardware.getMemory();
		mem.setTotal(memory.getTotal());
        mem.setUsed(memory.getTotal() - memory.getAvailable());
        mem.setFree(memory.getAvailable());
		return mem;
	}

	private Cpu initCpu() {
		CpuInfo cpuInfo = OshiUtil.getCpuInfo();
		Cpu cpu = new Cpu();
		
		int physicalProcessorCount = OshiUtil.getProcessor().getPhysicalProcessorCount();
		
		cpu.setCpuNum(physicalProcessorCount);
		cpu.setProcessNum(cpuInfo.getCpuNum());
		cpu.setTotal(cpuInfo.getToTal());
		cpu.setSys(cpuInfo.getSys());
		cpu.setUsed(cpuInfo.getUsed());
		cpu.setWait(cpuInfo.getWait());
		cpu.setFree(cpuInfo.getFree());
		cpu.setCpuModel(StringUtils.split(cpuInfo.getCpuModel(),"\n")[0]);
		return cpu;
	}
	
	private Jvm initJvm() {
		Jvm jvm = new Jvm();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
		return jvm;
	}
	
	

}
