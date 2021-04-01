package com.example.graphql.constant;


public final class Constant {

    public static class DefaultValue {
        public static final String LANG_CD = "ko";
        public static final String SYSTEM_NAME = "CBP";
        public static final int CHUNK_SIZE = 1000;
    }

    public static class ThreadLocal {
        public static final String REQUEST_INFO = "requestInfo";
        public static final String PAGE_PARAMETER_INFO = "pageParameterInfo";
    }

    public static class Basic {
        public static final String TRUE = "true";
        public static final String FALSE = "false";
        public static final String EMPTY = "";
        public static final String Y = "Y";
        public static final String N = "N";
        public static final String COLON = ":";
        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int TWO = 2;
        public static final int THREE = 3;
        public static final int TEN = 10;
        public static final int HUNDRED = 100;
    }

    public static class HeaderName {
        public static final String LANGUAGE = "Language";
        public static final String ACCESS_TOKEN = "Authorization";
        public static final String TEANTN_ID = "Tenant-Id";
        public static final String SERVICE_ID = "Service-Id";
        public static final String API_KEY = "Api-Key";
        public static final String ACCESS_TYPE = "bearer";
        public static final String ACCESS_AUTH_TYPE = "BASIC";
    }

    public static class DateFormat {
        public static final String BASIC_FORMAT = "yyyy/MM/dd HH:mm";
        public static final String BASIC_FORMAT_S = "yyyy/MM/dd HH:mm:ss";
        public static final String BASIC_FORMAT_WITH_TIMEZONE = "yyyy/MM/dd HH:mm:ss 'GMT'ZZ";
        public static final String FULL_FORMAT = "yyyy/MM/dd HH:mm:ss:SSSSSS 'GMT'ZZZZ";
        public static final String Y_M_D_H_M_FORMAT = "yyyyMMddHHmm";
        public static final String Y_M_D_H_M_S_FORMAT = "yyyyMMddHHmmss";
        public static final String Y_M_D_FORMAT = "yyyy/MM/dd";
        public static final String H_M_S_FORMAT = "HH:mm:ss";
    }

    public static class Query {

        public static final String TYPE = "QUERY_TYPE";
        public static final String SELECT = "select";
        public static final String SELECT_LIST = "selectList";
        public static final String UPDATE = "update";
        public static final String CHANGE_TIME_ZONE = "changeTimeZone";

    }

    public static class Swagger {
        public static final String RESOURCE_UI = "swagger-ui.html";
        public static final String RESOURCE_JARS = "/webjars/**";
        public static final String RESOURCE_UI_PATH = "classpath:/META-INF/resources/";
        public static final String RESOURCE_JARS_PATH = "classpath:/META-INF/resources/webjars/";
        public static final String VERSION = "v1";
    }

    public static class ApiOpen {
        public static final String EXTERNAL = "EXTERNAL";
        public static final String INTERNAL = "INTERNAL";
    }

    public static class Page {
        public static final String PAGE_NO = "pageNo";
        public static final String PAGE_SIZE = "pageSize";
        public static final String TOTAL_CNT = "totalCnt";
        public static final String SORT_IDX = "sortKey";
        public static final String SORT_ORD = "sortVal";
        public static final String SORT_METHOD_DESC = "D";
        public static final String SORT_METHOD_ASC = "A";
        public static final int DEF_PAGE_SIZE = 20;
    }

}
