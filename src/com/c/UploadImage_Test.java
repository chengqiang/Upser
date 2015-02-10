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
package com.c;

import java.util.ArrayList;

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年2月10日 上午9:40:37
 */
public class UploadImage_Test {
    // @Test
    public void normal() throws Exception {
        // 设定服务地址
        String serverUrl = "http://localhost:8081/Upser/servlet/Multiparts";// 上传地址

        // 设定要上传的普通Form Field及其对应的value
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        ffkvp.add(new FormFieldKeyValuePair("phone", "123456789"));// 其他参数
        /*
         * String receive1=RandomUtils.getRandomPhone();
         * String receive2=RandomUtils.getRandomPhone();
         * ffkvp.add(new FormFieldKeyValuePair("receiver",
         * receive1+"|"+receive2));
         */
        ffkvp.add(new FormFieldKeyValuePair("type", "png"));

        // 设定要上传的文件
        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
        ufi.add(new UploadFileItem("image", System.getProperty("user.dir")
                + "/src/test/resources/123.jpg"));
        HttpPostEmulator hpe = new HttpPostEmulator();
        String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, ufi);
        System.out.println("Responsefrom server is: " + response);

        // 对 imageUrl、thumbnailUrl、shortUrl进行获取，不能返回空
        /*
         * HttpClient httpClient = new HttpClient();
         * GetMethod getMethod = new GetMethod(imageUrl);
         * if (httpClient.executeMethod(getMethod) != HttpStatus.SC_OK) {
         * Assert.fail("imageUrl 内容不存在.");
         * }
         */

    }
}
