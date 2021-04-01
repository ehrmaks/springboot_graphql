package com.example.graphql.util;

import com.example.graphql.domain.vo.AccountVo;
import com.example.graphql.support.StaticContextAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

/**
 * Redis Util
 */
@Component
public class RedisUtil {


    static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

//	@SuppressWarnings("unchecked")
//	private static RedisTemplate<String, Object> userRedisTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("userRedisTemplate");

//	public static void accoutPush(String key , Object value) {
//		userRedisTemplate.opsForValue().set(key , value);
//	}
//
//	public static Object accoutGet(String key) {
//		return userRedisTemplate.opsForValue().get(key);
//	}

    // 20201218 레디스 SMS AccessToken 저장
    public void putSmsToken (String AccessToken) {
        RedisTemplate<String, Object> smsTokenTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("smsTokenTemplate");
        Duration expireTime = Duration.ofHours(3).plusMinutes(30);
        smsTokenTemplate.opsForValue().set("smsAccessToken", AccessToken, expireTime);
    }
    // 20201218 레디스 SMS AccessToken 가져오기
    public String getSmsToken () {
        RedisTemplate<String, Object> smsTokenTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("smsTokenTemplate");
        return (String) smsTokenTemplate.opsForValue().get("smsAccessToken");
    }

    // API ROLE 정보 관리
    public void putApiRoleAll(Map<String, String[]> data) {
        RedisTemplate<String, Object> apiRoleRedisTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("apiRoleRedisTemplate");
        apiRoleRedisTemplate.opsForValue().multiSet(data);
    }

    public void putApiRole(String key, String[] value) {
        RedisTemplate<String, Object> apiRoleRedisTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("apiRoleRedisTemplate");
        apiRoleRedisTemplate.opsForValue().set(key, value);
    }

    public String[] getApiRole(String key) {
        RedisTemplate<String, Object> apiRoleRedisTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("apiRoleRedisTemplate");
        return (String[]) apiRoleRedisTemplate.opsForValue().get(key);
    }

    public void removeApiRole(String key) {
        RedisTemplate<String, Object> apiRoleRedisTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("apiRoleRedisTemplate");
        apiRoleRedisTemplate.delete(key);
    }

    // Acoount 정보 관리
    public void putAcount(String key, AccountVo accountVo) {
        RedisTemplate<String, Object> loginUserRedisTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("loginUserRedisTemplate");


        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(accountVo);
        } catch (JsonProcessingException e) {
            logger.error("put account info error : " + accountVo.toString());
        }
        loginUserRedisTemplate.opsForValue().set(key, json);
        loginUserRedisTemplate.expireAt(key, Date.from(ZonedDateTime.now().plusHours(1).toInstant()));
    }

    public boolean changeExpireTimeForAccount(String key,int hours){
        RedisTemplate<String, Object> loginUserRedisTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("loginUserRedisTemplate");

        return loginUserRedisTemplate.expireAt(key, Date.from(ZonedDateTime.now().plusSeconds(hours).toInstant()));

    }

    public AccountVo getAcount(String key) {

        logger.debug("getAcount : " + key);

        RedisTemplate<String, Object> loginUserRedisTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("loginUserRedisTemplate");

        String json = (String) loginUserRedisTemplate.opsForValue().get(key);
        AccountVo accountVo = new AccountVo();

        if (StringUtil.isNotEmpty(json)) {
            accountVo = stringToObject(json, AccountVo.class);
        } else {
            logger.debug("account info is null : " + key);
            return accountVo;
        }
        return accountVo;
    }

    public boolean removeAcount(String key) {
        RedisTemplate<String, Object> userRedisTemplate = (RedisTemplate<String, Object>) StaticContextAccessor.getBeanByName("loginUserRedisTemplate");
        return userRedisTemplate.delete(key);
    }

    private <T> T stringToObject(String jsonString, Class<T> valueType) {
        try {
            return new ObjectMapper().readValue(jsonString, valueType);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
