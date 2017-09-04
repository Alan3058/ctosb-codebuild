package com.ctosb.codebuild.util;

import java.util.HashMap;
import java.util.Map;

/**
 * jdbc类型和java类型对应
 * 
 * @author Alan
 * @date 2015-11-21 上午01:03:26
 * 
 */
public class TypeMappingUtil {

    private static Map<String, String> TYPEMAP = new HashMap<String, String>();

    static {
        TYPEMAP.put("CHAR", "String");
        TYPEMAP.put("VARCHAR", "String");
        TYPEMAP.put("LONGVARCHAR", "String");
        TYPEMAP.put("NUMERIC", "BigDecimal");
        TYPEMAP.put("DECIMAL", "BigDecimal");
        TYPEMAP.put("BIT", "Integer");
        TYPEMAP.put("TINYINT", "Integer");
        TYPEMAP.put("SMALLINT", "Integer");
        TYPEMAP.put("INTEGER", "Integer");
        TYPEMAP.put("INT", "Integer");
        TYPEMAP.put("BIGINT", "Long");
        TYPEMAP.put("REAL", "Float");
        TYPEMAP.put("FLOAT", "Float");
        TYPEMAP.put("DOUBLE", "Double");
        TYPEMAP.put("BINARY", "Byte[]");
        TYPEMAP.put("VARBINARY", "Byte[]");
        TYPEMAP.put("LONGVARBINARY", "Byte[]");
        TYPEMAP.put("DATE", "Date");
        TYPEMAP.put("DATETIME", "Date");
        TYPEMAP.put("TIME", "Time");
        TYPEMAP.put("TIMESTAMP", "Timestamp");
    }

    public static void register(String key, String val) {
        TYPEMAP.put(key, val);
    }

    public static void registerAll(Map<String, String> map) {
        TYPEMAP.putAll(map);
    }

    public static String getType(String key) {
    	//数据库类型还包含有unsigned，所以用空格剔除掉
    	key = key.split(" ")[0];
        if (TYPEMAP.containsKey(key)) {
            return TYPEMAP.get(key);
        } else {
            return "String";
        }
    }

}
