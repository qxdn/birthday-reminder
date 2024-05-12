package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {

    private Long userId;

    private String password;

    private String confirmPassword;
}
