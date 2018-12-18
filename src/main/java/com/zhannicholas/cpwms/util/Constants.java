package com.zhannicholas.cpwms.util;

public class Constants {
    private Constants(){}

    // 查询类型
    public static final String SEARCH_BY_ID = "searchByID";
    public static final String SEARCH_BY_NAME = "searchByName";
    public static final String SEARCH_ALL = "searchAll";
    public static final String SEARCH_BY_ADDRESS = "searchByAddress";
    public static final String SEARCH_BY_REPOSITORY = "searchByRepositoryID";

    // 返回信息相关
    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_ERROR = "error";

    // response 中可能使用的值
    private static final String RESPONSE_RESULT = "result";
    private static final String RESPONSE_MSG = "msg";
    private static final String RESPONSE_DATA = "data";
    private static final String RESPONSE_TOTAL = "total";
}
