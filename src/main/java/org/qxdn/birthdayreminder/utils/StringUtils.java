package org.qxdn.birthdayreminder.utils;

import java.util.List;

public class StringUtils {
    public static List<String> split(String str, String separator) {
        return List.of(str.split(separator));
    }

    public static String join(List<String> list, String separator) {
        return String.join(separator, list);
    }
}
