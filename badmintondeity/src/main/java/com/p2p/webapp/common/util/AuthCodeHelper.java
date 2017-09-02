package com.p2p.webapp.common.util;

/**
 * <br>
 * 歉意过来的目前没有用到
 * @author <a href="mailto:eoems@sina.com">LiZhenZhong</a> at 2010-11-6 下午08:51:31
 * @version 1.0
 */
public class AuthCodeHelper {

	/**
	 * @param userPurview 
	 * @param optPurview
	 * @return
	 */
	public static boolean hasAuth(long userPurview, long optPurview) {
		return (userPurview & optPurview) == optPurview;
	}

}
