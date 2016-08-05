package com.redstar.foundation.utils;

import com.google.gson.Gson;
import com.redstar.foundation.BaseApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cody.yi on 2016/7/29.
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
     * @param resId json对应的资源id
     * @param clazz 解析的对象类
     * @param <T> 泛型
     * @return 返回对应泛型的List
     */
    public <T>List<T> getConfig(Integer resId , Class<T> clazz) {
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
        StringBuffer jsonStr = new StringBuffer();
        InputStream in = BaseApplication.getContext().getResources().openRawResource(resId);
        BufferedReader br;
        String rs;
        try {
            br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            if (br == null){
                return null;
            }
            while ((rs = br.readLine()) != null) {
                jsonStr.append(rs);
            }
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = CommonUtil.getType(List.class,clazz);
        List<T> config = mGson.fromJson(jsonStr.toString(),type);
        return config;
    }
}
