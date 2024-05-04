package org.qxdn.birthdayreminder.context;

import org.qxdn.birthdayreminder.model.dto.response.vo.UserSessionVO;

public class UserSessionContext {

    private static final ThreadLocal<UserSessionVO> userSessionThreadLocal = new ThreadLocal<>();

    public static void set(UserSessionVO userSessionVO) {
        userSessionThreadLocal.set(userSessionVO);
    }

    public static UserSessionVO get() {
        return userSessionThreadLocal.get();
    }

    public static void remove() {
        userSessionThreadLocal.remove();
    }

}
