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
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年2月9日 下午5:03:00
 */
public class HttpPostArgumentTest2 {

    // file1与file2在同一个文件夹下 filepath是该文件夹指定的路径
    public void SubmitPost(String url, String filename1, String filename2,
            String filepath) {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpPost httppost = new HttpPost(url);
            FileBody bin = new FileBody(new File(filepath + File.separator
                    + filename1));
            FileBody bin2 = new FileBody(new File(filepath + File.separator
                    + filename2));

            StringBody comment = new StringBody(filename1);

            MultipartEntity reqEntity = new MultipartEntity();

            reqEntity.addPart("file1", bin);// file1为请求后台的File upload;属性
            reqEntity.addPart("file2", bin2);// file2为请求后台的File upload;属性
            reqEntity.addPart("filename1", comment);// filename1为请求后台的普通参数;属性
            httppost.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(httppost);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {

                System.out.println("服务器正常响应.....");

                HttpEntity resEntity = response.getEntity();

                System.out.println(EntityUtils.toString(resEntity));// httpclient自带的工具类读取返回数据

                System.out.println(resEntity.getContent());

                EntityUtils.consume(resEntity);
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                httpclient.getConnectionManager().shutdown();
            } catch (Exception ignore) {

            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HttpPostArgumentTest2 httpPostArgumentTest2 = new HttpPostArgumentTest2();
        httpPostArgumentTest2.SubmitPost(
                "http://localhost:8081/UPser/receiveData.action", "a.text",
                "a.zip", "D://a");
    }

}
