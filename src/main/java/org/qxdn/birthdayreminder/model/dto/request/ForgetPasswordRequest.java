package org.qxdn.birthdayreminder.model.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ForgetPasswordRequest {

    @Email(message = "邮箱不能为空")
    private String email;
}
