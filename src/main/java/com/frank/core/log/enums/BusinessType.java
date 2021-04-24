package com.frank.core.log.enums;

/**
 * 业务操作类型
 * @author Frank
 *
 */
public enum BusinessType
{
	
	// 按顺序排列操作类型： 0-其他，1-查询，2-新增，3-修改，4-删除，5-导入，6-导出，7-授权
	
    /**
     * 其它
     */
    OTHER,
    
    /**
     * 查询
     */
    SELECT,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 导入
     */
    IMPORT,
    
    /**
     * 导出
     */
    EXPORT,
    
    /**
     * 授权
     */
    GRANT,


}
