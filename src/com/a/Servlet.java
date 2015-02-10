/**
 * This document and its contents are protected by copyright 2012 and owned by
 * Melot Inc.
 * The copying and reproduction of this document and/or its content (whether
 * wholly or partly) or any
 * incorporation of the same into any other material in any media or format of
 * any kind is strictly prohibited.
 * All rights are reserved.
 * Copyright (c) Melot Inc. 2015
 */
package com.a;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年2月9日 下午5:05:36
 */
public class Servlet {
    private Logger logger = Logger.getLogger(Servlet.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        response.setContentType("text/html;charset=UTF-8");
        Map map = new HashMap();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        File directory = null;
        List<FileItem> items = new ArrayList();
        try {
            items = upload.parseRequest(request);
            // 得到所有的文件
            Iterator<FileItem> it = items.iterator();
            while (it.hasNext()) {
                FileItem fItem = (FileItem) it.next();
                String fName = "";
                Object fValue = null;
                if (fItem.isFormField()) { // 普通文本框的值
                    fName = fItem.getFieldName();
                    // fValue = fItem.getString();
                    fValue = fItem.getString("UTF-8");
                    map.put(fName, fValue);
                } else { // 获取上传文件的值
                    fName = fItem.getFieldName();
                    fValue = fItem.getInputStream();
                    map.put(fName, fValue);
                    String name = fItem.getName();
                    if (name != null && !("".equals(name))) {
                        name = name
                                .substring(name.lastIndexOf(File.separator) + 1);

                        // String stamp =
                        // StringUtils.getFormattedCurrDateNumberString();
                        long timestamp_Str = new Date().getTime();
                        directory = new File("d://a");
                        directory.mkdirs();

                        String filePath = ("d://a") + timestamp_Str
                                + File.separator + name;
                        map.put(fName + "FilePath", filePath);

                        InputStream is = fItem.getInputStream();
                        FileOutputStream fos = new FileOutputStream(filePath);
                        byte[] buffer = new byte[1024];
                        while (is.read(buffer) > 0) {
                            fos.write(buffer, 0, buffer.length);
                        }
                        fos.flush();
                        fos.close();
                        map.put(fName + "FileName", name);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("读取http请求属性值出错!");
            // e.printStackTrace();
            logger.error("读取http请求属性值出错");
        }

        // 数据处理

        try {
            out = response.getWriter();
            out.print("{success:true, msg:'接收成功'}");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
