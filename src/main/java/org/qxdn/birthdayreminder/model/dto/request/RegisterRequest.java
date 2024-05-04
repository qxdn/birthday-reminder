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
    private String name;

    /**
     * 密码
     */
    private String password;
}
