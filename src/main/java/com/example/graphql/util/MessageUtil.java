package com.example.graphql.util;

import com.example.graphql.constant.Constant;
import com.example.graphql.support.StaticContextAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import java.util.Locale;

public class MessageUtil {

    static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    private static MessageSource messageSource = StaticContextAccessor.getBean(ReloadableResourceBundleMessageSource.class);

    public static String getMessage(String msgId) {
        return getMessage(msgId, new String[] {});
    }

    public static String getMessage(String msgId, Object msgArgs) {
        return getMessage(msgId, new Object[] {msgArgs}, null);
    }

    public static String getMessage(String msgId, Object[] msgArgs) {
        return getMessage(msgId, msgArgs, null);
    }

    public static String getMessage(String msgId, Object[] msgArgs, String basicText) {
        return StringUtil.getMessage(getOriginMessage(msgId, basicText), msgArgs);
    }

    private static String getOriginMessage(String msgKey, String basicText) {

        Locale locale = getLocale();

        if (StringUtils.isEmpty(msgKey)) {
            if (StringUtils.isEmpty(basicText)) {
                return "";
            } else {
                return basicText;
            }
        }
        if (StringUtils.isEmpty(basicText)) {
            basicText = "";
        }
        try {
            return messageSource.getMessage(msgKey, null, locale);
        } catch (NoSuchMessageException e) {
            return basicText;
        }
    }

    public static String getMessageByLanguage(String msgId, String langCd) {
        return getMessageByLanguage(msgId, null, langCd);
    }

    public static String getMessageByLanguage(String msgId, Object[] msgArgs, String langCd) {
        Locale locale = StringUtils.parseLocaleString(langCd);
        if (StringUtils.isEmpty(msgId)) {
            return "";
        }
        try {
            return StringUtil.getMessage(messageSource.getMessage(msgId, null, locale), msgArgs);
        } catch (NoSuchMessageException e2) {
            return "";
        }
    }

    private static Locale getLocale() {
        String langCd = Constant.DefaultValue.LANG_CD;
        if (ThreadLocalUtil.getRequestInfo() != null) {
            if (StringUtil.isNotEmpty(ThreadLocalUtil.getRequestInfo().getLanguage())) {
                langCd = ThreadLocalUtil.getRequestInfo().getLanguage();
            }
        }
        return StringUtils.parseLocaleString(langCd);
    }

}