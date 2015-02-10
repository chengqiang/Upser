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
package com.e;

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
 * @since 2015年2月10日 上午11:12:57
 */
public class HttpClientUploadFileTest {
    public void uploadFile(File file, String url) {
        if (!file.exists()) {
            return;
        }
        PostMethod postMethod = new PostMethod(url);
        try {
            // FilePart：用来上传文件的类
            FilePart fp = new FilePart("filedata", file);
            Part[] parts = { fp };

            // 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
            MultipartRequestEntity mre = new MultipartRequestEntity(parts,
                    postMethod.getParams());
            postMethod.setRequestEntity(mre);
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams()
                    .setConnectionTimeout(50000);// 设置连接时间
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                System.out.println(postMethod.getResponseBodyAsString());
            } else {
                System.out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HttpClientUploadFileTest test = new HttpClientUploadFileTest();
        test.uploadFile(new File("a:/a.text"), "http://localhost:8081/UPser/");
    }

}
