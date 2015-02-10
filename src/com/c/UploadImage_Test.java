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
 * @author ��ǿ<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015��2��10�� ����9:40:37
 */
public class UploadImage_Test {
    // @Test
    public void normal() throws Exception {
        // �趨�����ַ
        String serverUrl = "http://localhost:8081/Upser/servlet/Multiparts";// �ϴ���ַ

        // �趨Ҫ�ϴ�����ͨForm Field�����Ӧ��value
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        ffkvp.add(new FormFieldKeyValuePair("phone", "123456789"));// ��������
        /*
         * String receive1=RandomUtils.getRandomPhone();
         * String receive2=RandomUtils.getRandomPhone();
         * ffkvp.add(new FormFieldKeyValuePair("receiver",
         * receive1+"|"+receive2));
         */
        ffkvp.add(new FormFieldKeyValuePair("type", "png"));

        // �趨Ҫ�ϴ����ļ�
        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
        ufi.add(new UploadFileItem("image", System.getProperty("user.dir")
                + "/src/test/resources/123.jpg"));
        HttpPostEmulator hpe = new HttpPostEmulator();
        String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, ufi);
        System.out.println("Responsefrom server is: " + response);

        // �� imageUrl��thumbnailUrl��shortUrl���л�ȡ�����ܷ��ؿ�
        /*
         * HttpClient httpClient = new HttpClient();
         * GetMethod getMethod = new GetMethod(imageUrl);
         * if (httpClient.executeMethod(getMethod) != HttpStatus.SC_OK) {
         * Assert.fail("imageUrl ���ݲ�����.");
         * }
         */

    }
}
