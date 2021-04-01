package com.example.graphql.util;

import com.example.graphql.constant.Constant;
import com.example.graphql.domain.vo.AccountVo;
import com.example.graphql.domain.vo.RequestInfoVo;
import com.example.graphql.domain.vo.RequestParameterInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Thread Local 처리
 */
public class ThreadLocalUtil {

    static final Logger logger = LoggerFactory.getLogger(ThreadLocalUtil.class);

    private static ThreadLocal<Map<Object, Object>> threadLocal = new ThreadLocal<Map<Object, Object>>();

    public static RequestInfoVo getRequestInfo() {
        return (RequestInfoVo) get(Constant.ThreadLocal.REQUEST_INFO);
// 임시
//        AccountVo account = new AccountVo();
//        account.setUserId("test");
//        account.setUserNm("test");
//        account.setLangCd("ko");
//        account.setTenantId("00001");
//        account.setCorpId("CORP00000000000000001");
//        String[] roles = {"R0001","R0002","R0003","R0004","R0005","R0006","R0007","R0008"};
//        RequestInfoVo reqVo = new RequestInfoVo();
//        reqVo.setAccessToken("accessToken");
//        reqVo.setAccount(account);
//        reqVo.setApiKey("apiKey");
//        reqVo.setServiceId("serviceId");
//        reqVo.setTenantId("00001");
//        return reqVo;
    }

    public static RequestParameterInfo getPageParameterInfo() {
        return (RequestParameterInfo) get(Constant.ThreadLocal.PAGE_PARAMETER_INFO);
    }

    public static AccountVo getAccount() {
        return ((RequestInfoVo) get(Constant.ThreadLocal.REQUEST_INFO)).getAccount();
// 임시
//    	AccountVo account = new AccountVo();
//    	account.setUserId("test");
//    	account.setUserNm("test");
//    	account.setLangCd("ko");
//        account.setTenantId("00001");
//        account.setCorpId("CORP00000000000000001");
//        String[] roles = {"R0001","R0002","R0003","R0004","R0005","R0006","R0007","R0008"};
//    	account.setRoles(roles);
//    	return account;
    }

    public static void addDefaultSort(String sortKey, String sortVal) {
        if (getPageParameterInfo() != null ) {
            if (StringUtil.isEmpty(getPageParameterInfo().getSortKey())) {
                getPageParameterInfo().setSortKey(sortKey);
                getPageParameterInfo().setSortVal(sortVal);
            } else {
                getPageParameterInfo().setSortKey(getPageParameterInfo().getSortKey().trim() + "," + sortKey);
                getPageParameterInfo().setSortVal(getPageParameterInfo().getSortVal().trim() + "," + sortVal);
            }
        }
    }

    private ThreadLocalUtil() {
        throw new AssertionError();
    }

    public static void add(Object key, Object object) {
        logger.debug("ThreadLocal add");
        getAll().put(key, object);
    }

    public static Object get(Object key) {
        return getAll().get(key);
    }

    public static boolean isExist(Object key) {
        Object object = getAll().get(key);
        return object != null;
    }

    public static void clear() {
        logger.debug("ThreadLocal clean");
        threadLocal.remove();
    }

    private static Map<Object, Object> getAll() {
        HashMap<Object, Object> sharedInfo;
        if (threadLocal.get() == null) {
            sharedInfo = new HashMap<Object, Object>();
            threadLocal.set(sharedInfo);
        }
        return (Map<Object, Object>) threadLocal.get();
    }

    private static Object[] getThreadLocalKeys() {
        String[] arrKeys = new String[getAll().size()];
        Iterator<Object> keyIter = getAll().keySet().iterator();
        for (int i = 0; keyIter.hasNext(); ++i) {
            arrKeys[i] = (String) keyIter.next();
        }
        return arrKeys;
    }

    private static int size() {
        return getAll().size();
    }

    private static Object[] getThreadLocalValues() {
        int size = size();
        Object[] arrKeys = getThreadLocalKeys();
        Object[] values = new Object[size];

        for (int i = 0; i < size; ++i) {
            values[i] = getAll().get(arrKeys[i]);
        }

        return values;
    }

    public static String getValuesToString() {
        int size = size();
        StringBuffer str = new StringBuffer();
        Object[] keys = getThreadLocalKeys();
        Object[] values = getThreadLocalValues();

        for (int i = 0; i < size; ++i) {
            str.append("  " + keys[i] + " = " + values[i] + " \n");
        }

        return str.toString();
    }
}
