package org.study.base.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ggf on 2016/11/17.
 */
public class GsonUtils {
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        List<T> mList = new ArrayList<>();
        JsonArray array = new JsonParser().parse(jsonData).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(gson.fromJson(elem, type));
        }
        //List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {}.getType());
        return mList;
    }
    
    /**
     * [简要描述]:对象转换成json string 不包含null
     * @param object
     * @return
     * @author ggf
     * @date 2017年8月15日下午3:04:32
     */
    public static String parseObjectToString(Object object) {
    	Gson gson = new Gson();
    	return gson.toJson(object);
    }
    
    /**
     * [简要描述]: 对象转换成json string 包含null
     * @param object
     * @return
     * @author ggf
     * @date 2017年8月15日下午3:04:24
     */
    public static String parseObjectToStringWithNull(Object object) {
    	GsonBuilder builder = new GsonBuilder();
    	Gson gsonByBuilder = builder.serializeNulls().create();
    	return gsonByBuilder.toJson(object);
    }
}
