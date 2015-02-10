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

/**
 * Title: TODO
 * <p>
 * Description:
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年2月10日 上午9:39:01
 */
public class FormFieldKeyValuePair {
    private static final long serialVersionUID = 1L;

    // The form field used for receivinguser's input,

    // such as "username" in "<inputtype="text" name="username"/>"

    private String key;

    // The value entered by user in thecorresponding form field,

    // such as "Patrick" the abovementioned formfield "username"

    private String value;

    public FormFieldKeyValuePair(String key, String value)

    {

        this.key = key;

        this.value = value;

    }

    public String getKey()

    {

        return key;

    }

    public void setKey(String key) {

        this.key = key;

    }

    public String getValue()

    {

        return value;

    }

    public void setValue(String value)

    {

        this.value = value;

    }
}
