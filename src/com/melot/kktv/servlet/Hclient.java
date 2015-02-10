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
 * @author ��ǿ<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015��2��10�� ����11:26:40
 */
public class Hclient {
    public static final Logger log = Logger.getLogger(Hclient.class);

    public static void main(String args[]) {
        String targetURL = null; // TODO ָ��URL
        File targetFile = null; // TODO ָ���ϴ��ļ�
        targetFile = new File("d:\\bd_logo1.png");
        targetURL = "http://localhost:8081/UPser/servlet/upServlet"; // servleturl
        // ��û��commons-codec-1.4-jar
        // ���ｫ�����
        PostMethod filePost = new PostMethod(targetURL);
        try {
            // ͨ�����·�������ģ��ҳ������ύ
            // filePost.setParameter("name", "����");
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
                System.out.println("�ϴ��ɹ�");
                // �ϴ��ɹ�
            } else {
                System.out.println("�ϴ�ʧ��");
                // �ϴ�ʧ��
            }
        } catch (Exception ex) {
            log.error("ִ��ʱ����:==>" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            filePost.releaseConnection();
        }
    }
}
