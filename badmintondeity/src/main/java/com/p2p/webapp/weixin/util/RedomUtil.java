package com.p2p.webapp.weixin.util;

import java.util.Random;

public class RedomUtil {
	/**
	 * 生成四位的随机数
	 * @return
	 */
	public static int geneRedom(){
		int x;//定义两变量
        Random ne=new Random();//实例化一个random的对象ne
        x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
        System.out.println("产生的随机数是:"+x);//输出
        return x;
	}

}
