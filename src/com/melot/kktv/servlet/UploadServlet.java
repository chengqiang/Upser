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
package com.melot.kktv.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年2月10日 上午11:27:46
 */
public class UploadServlet extends HttpServlet {
    /**
     * 编号
     */
    private static final long serialVersionUID = 1L;
    private String uploadPath = "D:\\a"; // 上传文件的目录
    private String tempPath = "d:\\a\\buffer\\"; // 临时文件目录
    File tempPathFile;

    public void init() throws ServletException {
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        File tempPathFile = new File(tempPath);
        if (!tempPathFile.exists()) {
            tempPathFile.mkdirs();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // Set factory constraints
            factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
            factory.setRepository(tempPathFile);// 设置缓冲区目录
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // Set overall request size constraint
            upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
            @SuppressWarnings("unchecked")
            List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
            Iterator<FileItem> i = items.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                String fileName = fi.getName();
                if (fileName != null) {
                    File fullFile = new File(fi.getName());
                    File savedFile = new File(uploadPath, fullFile.getName());
                    fi.write(savedFile);
                }
            }
            System.out.print("upload succeed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // 可以跳转出错页面
            e.printStackTrace();
        }
    }
}
