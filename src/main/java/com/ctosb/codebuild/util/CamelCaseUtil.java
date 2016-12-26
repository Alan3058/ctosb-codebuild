package com.ctosb.codebuild.util;


/**
 * 驼峰写法帮助类
 * 
 * @author alan
 * @date 2014-6-12 下午6:10:16
 */
public class CamelCaseUtil {

    // 驼峰分隔符
    private static final char SEPARATOR = '_';

    /**
     * 驼峰写法转化成下斜杠（“_”）写法. 例如userName->user_name
     * 
     * @param origin
     * @return
     * @author alan
     * @date 2014-6-12 下午6:12:02
     */
    public static String toUnderlineName(String origin) {
        if (origin == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        // 采用双指针（i和j类似于指针）方法来遍历
        int i = -1;
        for (int j = 0; j < origin.length(); j++) {
            char c = origin.charAt(j);

            if (!Character.isUpperCase(c)) {
                // 当前字母为小写，则直接追加
                sb.append(c);
                continue;
            }
            // 否则当前字母为大写
            if (i == -1) {
                // 如果前面字母不是大写，则前面指针定位当前位置
                i = j;
            }
            if (j + 1 <= origin.length() - 1 && !Character.isUpperCase(origin.charAt(j + 1))) {
                // 下一个字母不是大写
                if (i == 0) {
                    // 处理第一个字母为大写的情况
                    if (i == j) {
                        // 如果只有第一个字母为大写,则追加第一个字母，类似于 Asdn 转换为 asdn
                        sb.append(c);
                    } else {
                        // 如果后面字母也大写,则类似于 ASDn转换as_dn
                        sb.append(origin.substring(i, j)).append(SEPARATOR).append(c);
                    }
                } else {
                    // 前面不是第一个字母，则先追加一个分隔符，类似于aSDFn 转换为 a_sd_fn
                    sb.append(SEPARATOR).append(origin.substring(i, j)).append(SEPARATOR).append(c);
                }
                i = -1;
            } else {
                // 如果下一个字母为大写，则继续循环
                // 如果不存在下一个字母，即当前字母为最后一个字母，并且大写，则追加
                if (j == origin.length() - 1) {
                    // 当前字母为最后一个字母，追加最后面几个字母
                    sb.append(SEPARATOR).append(origin.substring(i, j + 1));
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 转化成驼峰写法，例如user_name转成userName
     * 
     * @param origin
     * @return
     * @author alan
     * @date 2014-6-12 下午6:15:00
     */
    public static String toCamelCase(String origin) {
        if (origin == null) {
            return null;
        }
        // 先将字母全部小写
        origin = origin.toLowerCase();
        StringBuilder sb = new StringBuilder(origin.length());
        String[] str = origin.split(SEPARATOR + "");
        for (int i = 0; i < str.length; i++) {
            if (i == 0) {
                // 第一个字符串首字母不需要大写
                sb.append(str[i]);
            } else {
                sb.append(str[i].substring(0, 1).toUpperCase() + str[i].substring(1));
            }
        }
        return sb.toString();
    }

    /**
     * 转化成驼峰写法，并且首字母大写，例如user_name转成UserName
     * 
     * @param origin
     * @return
     * @author alan
     * @date 2014-6-12 下午6:16:50
     */
    public static String toUpperCamelCase(String origin) {
        if (origin == null) {
            return null;
        }
        // 先将字母全部小写
        origin = origin.toLowerCase();
        StringBuilder sb = new StringBuilder(origin.length());
        String[] str = origin.split(SEPARATOR + "");
        for (int i = 0; i < str.length; i++) {
            // 首字母大写追加
            sb.append(str[i].substring(0, 1).toUpperCase()).append(str[i].substring(1));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(CamelCaseUtil.toUnderlineName("ISOCertifiedStafF"));
        System.out.println(CamelCaseUtil.toUnderlineName("CertifiedStaFF"));
        System.out.println(CamelCaseUtil.toUnderlineName("UserID"));
        System.out.println(CamelCaseUtil.toCamelCase("iso_certified_staff"));
        System.out.println(CamelCaseUtil.toCamelCase("certified_staff"));
        System.out.println(CamelCaseUtil.toCamelCase("user_id"));
    }
}
