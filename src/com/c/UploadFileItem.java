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

import java.io.Serializable;

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年2月10日 上午9:39:17
 */
public class UploadFileItem implements Serializable {
    private static final long serialVersionUID = 1L;

    // The form field name in a form used foruploading a file,

    // such as "upload1" in "<inputtype="file" name="upload1"/>"

    private String formFieldName;

    // File name to be uploaded, thefileName contains path,

    // such as "E:\\some_file.jpg"

    private String fileName;

    public UploadFileItem(String formFieldName, String fileName)

    {

        this.formFieldName = formFieldName;

        this.fileName = fileName;

    }

    public String getFormFieldName()

    {

        return formFieldName;

    }

    public void setFormFieldName(String formFieldName)

    {

        this.formFieldName = formFieldName;

    }

    public String getFileName()

    {

        return fileName;

    }

    public void setFileName(String fileName)

    {

        this.fileName = fileName;

    }
}
