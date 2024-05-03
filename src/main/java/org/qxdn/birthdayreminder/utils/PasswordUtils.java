package org.qxdn.birthdayreminder.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.qxdn.birthdayreminder.model.constants.BirthdayConstants;

public class PasswordUtils {

    public static String encode(String password) {
        return BCrypt.hashpw(password, BirthdayConstants.PASSWORD_SALT);
    }

    public static boolean check(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
