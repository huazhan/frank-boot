package com.frank.framework.util;

/**
 * 转换工具类
 * @ClassName ConvertUtil
 * @Author chenhuazhan
 * @Date: 2021年5月26日 上午11:12:23 
 * @Description: TODO
 * @Version 1.0
 */
public class ConvertUtil {

	/**
	 * 容量转换
	 * @Author chenhuazhan
	 * @Date: 2021年5月26日 上午11:12:43 
	 * @Description: TODO
	 * @param size
	 * @return
	 */
	public static String convertCapacity(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB" , (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB" , f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB" , f);
        } else {
            return String.format("%d B" , size);
        }
    }
}
