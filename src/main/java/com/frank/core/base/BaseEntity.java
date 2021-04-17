package com.frank.core.base;

import java.io.Serializable;
import java.util.Date;

import com.frank.framework.util.UserUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.hutool.core.date.DateUtil;

/**
 * 公共Entity
 * @author Frank
 *
 */
public class BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Integer page;
	
	@JsonIgnore
	private Integer limit;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime = DateUtil.date();
	
	private String createBy = UserUtils.getCurrentUsername();
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime = DateUtil.date();
	
	private String updateBy = UserUtils.getCurrentUsername();
	
	private String remark;
	

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
