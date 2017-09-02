package com.p2p.webapp.common.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



/**
 * 
 * 链接需要对接的系统的数据库
 * 
 * @author aibozeng
 *
 */

public class DataSourceTool {
	
	//易桥系统的DB
	private static String ET_DS = "jdbc/OracleDS4ET";
	
	public static DataSource getDataSource(){
		return getDataSource(ET_DS);
	}

	public static DataSource getDataSource(String ds_name){
		DataSource ds = null; 
		InitialContext context=null;
		Properties p = new Properties();
		try {
			context = new InitialContext();           //JNDIPrefix           
		} catch (NamingException e) {
			e.printStackTrace();
		}
		try{
			ds = (DataSource) context.lookup( ds_name );
		}
		catch(Exception e){				
			System.out.println("lookup "+ds_name+" is not found");
			ds = null;
		}
		try{
			if(ds==null){
				System.out.println("begin to find java:/"+ds_name);
				ds = (DataSource) context.lookup( "java:/"+ds_name );				
			}
		}
		catch(Exception e){				
			System.out.println("lookup java:/"+ds_name+" is not found");
		}
		if(ds!=null){
			System.out.println("ok."+ds);
		}
		return ds;
	}
	
	public static Connection getConnection(){
		DataSource ds = getDataSource();
		if(ds==null){
			System.out.println("ds is null");
			return null;
		}
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		conn = DataSourceTool.getConnection();
		System.out.println("read conn is "+conn);
	}

}
