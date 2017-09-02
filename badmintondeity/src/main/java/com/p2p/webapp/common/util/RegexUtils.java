package com.p2p.webapp.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	
	public static boolean checkPhoneNum (String mobile){
		if (mobile == null){
			return false;
		}
		String regex = "^((\\+86))?(0)?(13[0-9]?|15[0-9]?|18[0-9])[0-9]{8}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mobile);
		if (!m.find()){
			return false;
		} else {
			return true;
		}
	}
}
