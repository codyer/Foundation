package com.chinaredstar.foundation.common.utils;

import com.chinaredstar.foundation.FoundationApplication;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cody.yi on 2016/7/29.
 * 从raw目录读取配置文件工具类
 */
public class ConfigUtil {
    private static ConfigUtil INSTANCE;
    private Map<Integer,List> mConfig;
    private Gson mGson;

    private ConfigUtil() {
        mConfig = new HashMap<>();
        mGson = new Gson();
    }

    public static ConfigUtil getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ConfigUtil();
        }
        return INSTANCE;
    }

    /**
     * 第一期从raw文件读取，后期可以改成从服务器获取，并做缓存
     * @param <T> 泛型
     * @param resId json对应的资源id
     * @param clazz 解析的对象类
     * @return 返回对应泛型的List
     */
    public <T>List getConfig(Integer resId , Class<T> clazz) {
        if (!mConfig.containsKey(resId)){
            List<T> config = readConfig(resId,clazz);
            mConfig.put(resId,config);
            return config;
        }
        return mConfig.get(resId);
    }

    /**
     * 第一期从raw文件读取，后期可以改成从服务器获取，并做缓存
     * @param resId json对应的资源id
     * @param clazz 解析的对象类
     * @param <T> 泛型
     * @return 返回对应泛型的List
     */
    private <T>List<T> readConfig(Integer resId, Class<T> clazz) {
        StringBuilder jsonStr = new StringBuilder();
        InputStream in = FoundationApplication.getContext().getResources().openRawResource(resId);
        BufferedReader br;
        String rs;
        try {
            br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            while ((rs = br.readLine()) != null) {
                jsonStr.append(rs);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = CommonUtil.getType(List.class,clazz);
        return mGson.fromJson(jsonStr.toString(),type);
    }
}
