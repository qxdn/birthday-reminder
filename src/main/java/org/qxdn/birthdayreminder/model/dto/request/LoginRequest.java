package org.qxdn.birthdayreminder.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 登录请求
 */
@Data
public class LoginRequest {

    @Length(min = 3, max = 20, message = "用户名长度必须在3-20之间")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度必须在6-20之间")
    private String password;
}
