package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;

/**
 * 注册请求
 */
@Data
public class RegisterRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;
}
