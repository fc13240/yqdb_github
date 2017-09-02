package com.p2p.webapp.common.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.util.UUIDHexGeneratorEx;

public class Upload extends HttpServlet {
    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(Upload.class);
    private static final long serialVersionUID = 1L;
    private static String filePath = "/static/images/upload";
    private String fileName;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 使用同一种处理方法
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 保存路径
        String savePath = getServletContext().getRealPath(filePath);
        File saveDir = new File(savePath);
        // 如果目录不存在，就创建目录
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }

        // 创建文件上传核心类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        // 设置编码
        sfu.setHeaderEncoding("UTF-8");
        // 设置上传的单个文件的最大字节数为2M
        sfu.setFileSizeMax(1024 * 1024 * 2);
        // 设置整个表单的最大字节数为10M
        sfu.setSizeMax(1024 * 1024 * 10);
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 处理表单请求
            List<FileItem> itemList = sfu.parseRequest(request);
            for (FileItem fileItem : itemList) {
                // 获得文件大小
                Long size = fileItem.getSize();
                // 获得文件名
                fileName = fileItem.getName();
                logger.debug("原文件名：" + fileName + "\t大小：" + size + "byte");
                String type = fileItem.getContentType();
                type = type.substring(type.lastIndexOf("/") + 1, type.length());

                fileName = UUIDHexGeneratorEx.gen18() + "." + type;
                logger.debug("改编后文件名：" + fileName + "\t大小：" + size + "byte");
                // 将文件保存到指定的路径
                File file = new File(savePath, fileName);
                fileItem.write(file);
                request.setAttribute("msg", "上传成功！");
                // 返回存储地址
                String logoUrl = "/p2pstock" + filePath + "/" + fileName;
                map.put("result", "0");
                map.put("picUrl", logoUrl);
                outJson(response, map);
            }
        } catch (FileSizeLimitExceededException e) {
            request.setAttribute("msg", "文件太大");
            map.put("result", "1");
            outJson(response, map);
        } catch (FileUploadException e) {
            request.setAttribute("msg", "图片上传失败");
            e.printStackTrace();
            map.put("result", "1");
            outJson(response, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @description 将单个对象转换json输出，非集合类
     * @version
     * @title 将单个对象转换json输出，非集合类
     * @author
     * @param obj
     *            Object 单个对象
     */
    public void outJson(HttpServletResponse response, Object obj) {
        String str = "";
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            str = JSONObject.fromObject(obj).toString();
            out.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}