package org.qxdn.birthdayreminder.utils;

import java.util.List;

public class StringUtils {
    public static List<String> split(String str, String separator) {
        return List.of(str.split(separator));
    }

    public static String join(List<String> list, String separator) {
        return String.join(separator, list);
    }

    public static boolean stringEquals(String str1, String str2) {
        return str1.equals(str2);
    }

    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }

    public static String leftPad(String str, int length, char padChar) {
        return org.apache.commons.lang3.StringUtils.leftPad(str,length,padChar);
    }
}
