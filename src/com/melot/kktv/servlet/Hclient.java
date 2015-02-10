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

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.log4j.Logger;

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年2月10日 上午11:26:40
 */
public class Hclient {
    public static final Logger log = Logger.getLogger(Hclient.class);

    public static void main(String args[]) {
        String targetURL = null; // TODO 指定URL
        File targetFile = null; // TODO 指定上传文件
        targetFile = new File("d:\\bd_logo1.png");
        targetURL = "http://localhost:8081/UPser/servlet/upServlet"; // servleturl
        // 若没有commons-codec-1.4-jar
        // 这里将会出错
        PostMethod filePost = new PostMethod(targetURL);
        try {
            // 通过以下方法可以模拟页面参数提交
            // filePost.setParameter("name", "中文");
            // filePost.setParameter("pass", "1234");
            Part[] parts = { new FilePart(targetFile.getName(), targetFile) };
            filePost.setRequestEntity(new MultipartRequestEntity(parts,
                    filePost.getParams()));
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams()
                    .setConnectionTimeout(5000);
            System.out.println(filePost.getPath() + "=="
                    + client.executeMethod(filePost));
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
                System.out.println("上传成功");
                // 上传成功
            } else {
                System.out.println("上传失败");
                // 上传失败
            }
        } catch (Exception ex) {
            log.error("执行时出现:==>" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            filePost.releaseConnection();
        }
    }
}
