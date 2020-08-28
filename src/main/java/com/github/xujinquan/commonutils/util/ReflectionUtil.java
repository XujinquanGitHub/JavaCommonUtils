package com.github.xujinquan.commonutils.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: commonutils
 * @description:
 * @author: 许金泉
 **/
public class ReflectionUtil {


    /**
    * 获取运行时指定类型的泛型对象
    * @param type 指定类型
    *  @param index  泛型下标
    * @return T
    * @author 许金泉
    * @date 2019/7/29 15:59createQrCode
    */
    public static  <T> T getModel(Class<?> type ,int index) {
        //1、返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
        Type genType = type.getGenericSuperclass();
        //2、泛型参数
        Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
        //3、因为 MyBaseServiceImpl 有两个泛型 数组有两个
        try {
            return (T) ((Class) types[1]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取运行时指定类型的泛型类型
     * @param type 指定类型
     *  @param index  泛型下标
     * @return T
     * @author 许金泉
     * @date 2019/7/29 15:59
     */
    public static  Class getClass(Class<?> type ,int index) {
        Type genType = type.getGenericSuperclass();
        Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
        return(Class) types[1];
    }


    @SuppressWarnings("unchecked")
    private static List<Field> getAllFields(Class clazz, List<Field> fields) {
        if (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            getAllFields(clazz.getSuperclass(), fields);
        }
        return fields;
    }

    public static List<Field> getAllFields(Class clazz) {
        return getAllFields(clazz, new ArrayList<>());
    }

}
