package com.bluelight.demo.web.controller;

import com.bluelight.demo.web.util.MenuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wechat")
public class MenuController {
	/**
	 * 创建菜单
	 * */
	@RequestMapping("/createMenu")
	public static void createMenu(){
		int status = MenuUtil.createMenu(MenuUtil.getMenu());
		if(status==0){
			System.out.println("菜单创建成功！");
		}else{
			System.out.println("菜单创建失败！");
		}
	}
	
}
