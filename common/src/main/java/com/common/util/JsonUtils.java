package com.common.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author qll
 * @create 2019-02-13 18:19
 * @desc jsonutils
 **/
public class JsonUtils {

    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) throws Exception{
        T t = MAPPER.readValue(jsonData, beanType);
        return t;
    }
    /**
     * 将对象转换成json字符串。
     */
    public static String objectToJson(Object data){
        String string = null;
        try {
            string = MAPPER.writeValueAsString(data);
        } catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 将json数据转换成pojo对象list
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) throws Exception{
        //此方法用于构造List的Java类型
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        List<T> list = MAPPER.readValue(jsonData, javaType);
        return list;
    }

}
