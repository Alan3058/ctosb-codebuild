package com.ctosb.codebuild.util;

import java.util.Collection;
import java.util.Map;

public class EmptyUtil {

    /**
     * 对象是否为空
     * 
     * @param object
     * @return
     * @author Alan
     * @time 2015-11-20 下午11:21:52
     */
    public static boolean isEmpty(Object object) {
        return object == null;
    }

    /**
     * 字符串是否为空
     * 
     * @param string
     * @return
     * @author Alan
     * @time 2015-11-20 下午11:28:59
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() <= 0;
    }

    /**
     * 字符串是否为空（去掉空字符）
     * 
     * @param string
     * @return
     * @author Alan
     * @time 2015-11-20 下午11:28:59
     */
    public static boolean isEmptyTrim(String string) {
        return string == null || string.trim().length() <= 0;
    }

    /**
     * 数组是否为空
     * 
     * @param objects
     * @return
     * @author Alan
     * @time 2015-11-20 下午11:30:27
     */
    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length <= 0;
    }

    /**
     * 集合是否为空
     * 
     * @param list
     * @return
     * @author Alan
     * @time 2015-11-20 下午11:22:04
     */
    public static boolean isEmpty(Collection<? extends Object> list) {
        return list == null || list.size() <= 0;
    }

    /**
     * map是否为空
     * 
     * @param map
     * @return
     * @author Alan
     * @time 2015-11-20 下午11:24:54
     */
    public static boolean isEmpty(Map<? extends Object, ? extends Object> map) {
        return map == null || map.size() <= 0;
    }


}
