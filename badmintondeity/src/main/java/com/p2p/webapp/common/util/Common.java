package com.p2p.webapp.common.util;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Common {
	private static final Log log = LogFactory.getLog(Common.class);

	public static boolean markDir(String path) {
		File dirFile  = null ;
		try  {
            dirFile  =   new  File(path);
             if (!(dirFile.exists())  &&   ! (dirFile.isDirectory()))  {
                 boolean  creadok  =  dirFile.mkdirs();
                 if (creadok)  {
                    log.debug(" ok:创建文件夹成功！文件路径：" + path);
                    return true;
                } else  {
                	log.error(" err:创建文件夹失败！ 文件路径：" + path);
                	return false;
                } 
            } 
         } catch (Exception e)  {
            e.printStackTrace();
            return false;
        } 
        return true;
	}
}
