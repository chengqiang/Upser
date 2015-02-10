/**
 * This document and its contents are protected by copyright 2012 and owned by Melot Inc.
 * The copying and reproduction of this document and/or its content (whether wholly or partly) or any
 * incorporation of the same into any other material in any media or format of any kind is strictly prohibited.
 * All rights are reserved.
 *
 * Copyright (c) Melot Inc. 2015
 */
package com.c;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
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
 * @since 2015年2月10日 上午9:43:03
 */
public class Test {
public static void main(String args[]) throws Exception {
        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,"----------ThIs_Is_tHe_bouNdaRY_$", Charset.defaultCharset());
        multipartEntity.addPart("phone",new StringBody("136********", Charset.forName("UTF-8")));  
        multipartEntity.addPart("receiver",new StringBody("138***********",Charset.forName("UTF-8")));  
        multipartEntity.addPart("image",new FileBody(new File(System.getProperty("user.dir")+"/src/test/resources/123.jpg"),"image/png"));  
        HttpPost request = new HttpPost("http://localhost:8081/Upser/servlet/Multiparts");   
        request.setEntity(multipartEntity);
        request.addHeader("Content-Type","multipart/form-data; boundary=----------ThIs_Is_tHe_bouNdaRY_$");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response =httpClient.execute(request);
        InputStream is = response.getEntity().getContent();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        System.out.println("发送消息收到的返回："+buffer.toString());
    }
}