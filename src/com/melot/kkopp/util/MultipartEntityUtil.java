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
package com.melot.kkopp.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年2月9日 下午4:54:04
 */
public class MultipartEntityUtil {
    public static String postFile(File file, String url)
            throws ClientProtocolException, IOException {

        FileBody bin = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        if (file != null) {
            bin = new FileBody(file);
        }
        StringBody username = new StringBody("13696900475");
        StringBody password = new StringBody("13696900475");
        // 请记住，这边传递汉字会出现乱码，解决方法如下,设置好编码格式就好
        // new StringBody("汉字",Charset.forName("UTF-8")));

        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("username", username);
        reqEntity.addPart("password", password);
        reqEntity.addPart("data", bin);

        httppost.setEntity(reqEntity);
        System.out.println("执行: " + httppost.getRequestLine());

        HttpResponse response = httpclient.execute(httppost);
        System.out.println("statusCode is "
                + response.getStatusLine().getStatusCode());
        HttpEntity resEntity = response.getEntity();
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
        if (resEntity != null) {
            System.out.println("返回长度: " + resEntity.getContentLength());
            System.out.println("返回类型: " + resEntity.getContentType());
            InputStream in = resEntity.getContent();
            System.out.println("in is " + in);
            // System.out.println(IoStreamUtil.getStringFromInputStream(in));
        }
        if (resEntity != null) {
            resEntity.consumeContent();
        }
        return null;
    }

    public static void main(String[] args) throws ClientProtocolException,
            IOException {

        File file = new File("d:/a.text");
        String url = "http://localhost:8080/webtest/servlet/URLTest";
        postFile(file, url);
    }

}
