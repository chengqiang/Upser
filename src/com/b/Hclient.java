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

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年2月9日 下午5:47:17
 */

public class Hclient {
    public static void main(String args[]) {
        String targetURL = null; // -- 指定URL
        File targetFile = null; // -- 指定上传文件
        targetFile = new File("D:\\a\\a.text");
        targetURL = "http://localhost:8081/Upser/servlet/Multiparts"; // servleturl
        PostMethod filePost = new PostMethod(targetURL);
        try {
            // 通过以下方法可以模拟页面参数提交
            Part[] parts = { new FilePart(targetFile.getName(), targetFile) };
            filePost.setRequestEntity(new MultipartRequestEntity(parts,
                    filePost.getParams()));
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams()
                    .setConnectionTimeout(5000);
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
                System.out.println("上传成功");
                // 上传成功
            } else {
                System.out.println("上传失败");
                // 上传失败
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            filePost.releaseConnection();
        }
    }
}