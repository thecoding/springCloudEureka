package com.order.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

/**
 * @author Mirko
 * @Description
 * @createTime 2019年10月11日 01:39:00
 */
public class JsonUtils {

    // json处理工具
    private static ObjectMapper mapper = new ObjectMapper();

    //对象转字符串
    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //字符串转对象
    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : mapper.readValue(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
