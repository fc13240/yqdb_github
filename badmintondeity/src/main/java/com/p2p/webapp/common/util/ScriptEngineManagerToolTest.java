package com.p2p.webapp.common.util;


public class ScriptEngineManagerToolTest {
	public ScriptEngineManagerToolTest(){		
	}

	//@Test
	public void test1() throws Exception {/*
		LoginUser loginUser = new LoginUser();
	    Map<String,LoginUser> map = new HashMap<String,LoginUser>();
	    map.put("loginUser",loginUser );
	    String script = " if(loginUser.userType.equals(\"0\")){" +
	            "  loginUser.setUserNm(\"超级管理员\"); "+
	    		"} ";
	    
	    ScriptEngineManagerTool.execScript(map, script);
	    
	    System.out.println(loginUser.getUserNm());	    
	*/}
	
	
	public final static  void main(String[] args){
		try {
			new ScriptEngineManagerToolTest().test1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
