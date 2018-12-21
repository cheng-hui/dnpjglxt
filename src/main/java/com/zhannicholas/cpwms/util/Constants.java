package com.zhannicholas.cpwms.util;

public class Constants {
    private Constants(){}

    // 查询类型
    public static final String SEARCH_BY_ID = "searchByID";
    public static final String SEARCH_BY_NAME = "searchByName";
    public static final String SEARCH_ALL = "searchAll";
    public static final String SEARCH_BY_ADDRESS = "searchByAddress";
    public static final String SEARCH_BY_REPOSITORY_ID = "searchByRepositoryID";
    public static final String SEARCH_BY_PARTS_ID = "searchByPartsID";
    public static final String SEARCH_BY_PARTS_NAME = "searchByPartsName";
    public static final String SEARCH_BY_PARTS_TYPE = "searchByPartsType";
    public static final String SEARCH_STOCK_IN_ONLY = "stockInOnly";
    public static final String SEARCH_STOCK_OUT_ONLY = "stockOutOnly";

    // 返回信息相关
    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_ERROR = "error";
    public static final String RESPONSE_RESULT = "result";
    public static final String RESPONSE_MSG = "msg";
    public static final String SERVER_ERROR = "serverError";

    // 用户状态
    public static final String ACCOUNT_STATUS = "account_status";
    public static final String SIGN_IN = "sign in";	// 已登录
    public static final String SIGN_OUT = "sign out";	// 已注销

    // 用户信息
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";
    public static final String USER_PASSWORD = "password";

    public static final String NONE = "none";

    // 密码相关
    public static final String PASSWORD_ERROR = "passwordError";
    public static final String PASSWORD_UNMATCH = "passwordUnmatched";
}
