package com.p2p.webapp.weixin.util;

/** 
 * 字符串的处理类可以把udmp迁移过来
 *
 * @author Lizhenzhong  eoems@siancom 
 */
public class StringUtils {
	public static boolean isNotBlank(String value){
		if(null != value&&!"".equals(value.trim())){
			return false;
		}else{
			return true;
		}
		
	}
}