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
package com.b;

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author ��ǿ<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015��2��9�� ����5:48:19
 */
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MultipartRequestEntity extends HttpServlet {
    private Log log = LogFactory.getLog(MultipartRequestEntity.class);
    private static final long serialVersionUID = 1L;
    private String uploadPath; // �ϴ��ļ���Ŀ¼
    private String tempPath; // ��ʱ�ļ�Ŀ¼
    File tempPathFile;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        uploadPath = config.getServletContext().getInitParameter(
                "imageStorePath");
        tempPath = config.getServletContext().getInitParameter("images_120");

        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        File tempPathFile = new File(tempPath);
        if (!tempPathFile.exists()) {
            tempPathFile.mkdirs();
        }
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            log.info("�����ϴ�ͼƬ��servlt");

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(4096); // ���û�������С��������4kb
            factory.setRepository(tempPathFile);// ���û�����Ŀ¼

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(4194304); // ��������ļ��ߴ磬������4MB
            List<FileItem> items = upload.parseRequest(request);// �õ����е��ļ�
            Iterator<FileItem> i = items.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                String fileName = fi.getName();
                log.info("�ϴ�ͼƬ�����֣�" + fileName);
                if (fileName != null) {
                    File fullFile = new File(fi.getName());
                    File savedFile = new File(uploadPath, fullFile.getName());
                    fi.write(savedFile);
                }
            }
            log.info("�ϴ��ɹ�!");
        } catch (Exception e) {
            log.info(e.getMessage());
            // ������ת����ҳ��
            e.printStackTrace();
        }
    }

}
