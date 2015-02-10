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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author ��ǿ<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015��2��10�� ����9:37:43
 */
public class HttpPostEmulator {
    // ÿ��post����֮��ķָ��������趨��ֻҪ������������ַ����ظ����ɡ�
    private static final String BOUNDARY = "----------HV2ymHFg03ehbqgZCaKO6jyH";

    public String sendHttpPostRequest(String serverUrl,
            ArrayList<FormFieldKeyValuePair> generalFormFields,
            ArrayList<UploadFileItem> filesToBeUploaded) throws Exception {

        // �����������post����

        URL url = new URL(serverUrl/* "http://127.0.0.1:8080/test/upload" */);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // ����POST�������������������

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + BOUNDARY);

        // ͷ

        String boundary = BOUNDARY;

        // ��������

        StringBuffer contentBody = new StringBuffer("--" + BOUNDARY);

        // β

        String endBoundary = "\r\n--" + boundary + "--\r\n";

        OutputStream out = connection.getOutputStream();

        // 1. ����������ʽ��POST����

        for (FormFieldKeyValuePair ffkvp : generalFormFields)

        {

            contentBody.append("\r\n")

            .append("Content-Disposition: form-data; name=\"")

            .append(ffkvp.getKey() + "\"")

            .append("\r\n")

            .append("\r\n")

            .append(ffkvp.getValue())

            .append("\r\n")

            .append("--")

            .append(boundary);

        }

        String boundaryMessage1 = contentBody.toString();

        out.write(boundaryMessage1.getBytes("utf-8"));

        // 2. �����ļ��ϴ�

        for (UploadFileItem ufi : filesToBeUploaded)

        {

            contentBody = new StringBuffer();

            contentBody.append("\r\n")

            .append("Content-Disposition:form-data; name=\"")

            .append(ufi.getFormFieldName() + "\"; ") // form��field������

                    .append("filename=\"")

                    .append(ufi.getFileName() + "\"") // �ϴ��ļ����ļ���������Ŀ¼

                    .append("\r\n")

                    .append("Content-Type:application/octet-stream")

                    .append("\r\n\r\n");

            String boundaryMessage2 = contentBody.toString();

            out.write(boundaryMessage2.getBytes("utf-8"));

            // ��ʼ�����������д�ļ�

            File file = new File(ufi.getFileName());

            DataInputStream dis = new DataInputStream(new FileInputStream(file));

            int bytes = 0;

            byte[] bufferOut = new byte[(int) file.length()];

            bytes = dis.read(bufferOut);

            out.write(bufferOut, 0, bytes);

            dis.close();

            contentBody.append("------------HV2ymHFg03ehbqgZCaKO6jyH");

            String boundaryMessage = contentBody.toString();

            out.write(boundaryMessage.getBytes("utf-8"));

            // System.out.println(boundaryMessage);

        }

        out.write("------------HV2ymHFg03ehbqgZCaKO6jyH--\r\n"
                .getBytes("UTF-8"));

        // 3. д��β

        out.write(endBoundary.getBytes("utf-8"));

        out.flush();

        out.close();

        // 4. �ӷ�������ûش������

        String strLine = "";

        String strResponse = "";

        InputStream in = connection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while ((strLine = reader.readLine()) != null)

        {

            strResponse += strLine + "\n";

        }

        // System.out.print(strResponse);

        return strResponse;

    }
}
