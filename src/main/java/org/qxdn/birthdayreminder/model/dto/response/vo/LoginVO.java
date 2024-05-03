package org.qxdn.birthdayreminder.model.dto.response.vo;

import lombok.Data;

@Data
public class LoginVO {

    /**
     * 用户信息
     */
    private UserVO user;

    /**
     * JWT token
     */
    private String token;
}
