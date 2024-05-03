package org.qxdn.birthdayreminder.utils;

import org.qxdn.birthdayreminder.model.enums.ErrorEnum;
import org.qxdn.birthdayreminder.model.exception.BirthdayException;

import java.util.Objects;

public class CheckUtils {

    /**
     * 检查非空
     * @param objects 检查对象
     * @return 是否非空
     */
    public static boolean notNull(Object... objects) {
        for(Object object : objects) {
            if(Objects.isNull(object)) {
                throw new BirthdayException(ErrorEnum.CHECK_FAIL);
            }
        }
        return true;
    }

    public static boolean checkEquals(Object o1, Object o2) {
        return checkEquals(o1,o2,null);
    }

    public static boolean checkEquals(Object o1, Object o2,String message) {
        if(!Objects.equals(o1, o2)) {
            if (Objects.isNull(message)) {
                throw new BirthdayException(ErrorEnum.CHECK_FAIL);
            } else {
                throw new BirthdayException(ErrorEnum.CHECK_FAIL,message);
            }
        }
        return true;
    }

    public static boolean checkTrue(Boolean expression) {
        if (Objects.isNull(expression) || !expression) {
            throw new BirthdayException(ErrorEnum.CHECK_FAIL);
        }
        return true;
    }

    /**
     * 检查字符串非空
     * @param strings 字符串
     * @return 是否非空
     */
    public static boolean notBlank(String... strings) {
        for(String string : strings) {
            if(Objects.isNull(string) || string.isEmpty()) {
                throw new BirthdayException(ErrorEnum.CHECK_FAIL);
            }
        }
        return true;
    }
}
