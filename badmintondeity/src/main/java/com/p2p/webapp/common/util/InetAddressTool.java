package com.p2p.webapp.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InetAddress[] iadds = InetAddressTool.getLocalIPs();
        for(InetAddress iadd : iadds){
        	System.out.println(iadd.getHostAddress());
        	System.out.println(iadd.getHostName());
        }
		System.out.println(InetAddressTool.getLocalIpv4());
	}
	
	/**
	 * 获取本机 IP  ipv4
	 * @return  192.168.42.29
	 */
	public static String getLocalIpv4(){
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}
	

	/**
	 * 获取本地主机ip列表 
	 * @return  
	 *  两个 192.168.42.29(ipv4)  fe80:0:0:0:f510:61a9:91b3:50fc%14(ipv6) 
	 */
	public static InetAddress[] getLocalIPs() {
		InetAddress[] mArLocalIP = null;
		try {
			mArLocalIP = InetAddress.getAllByName(InetAddress.getLocalHost()
					.getHostName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mArLocalIP;
	}

}
