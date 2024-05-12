package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;

@Data
public class UpdateUserRequest {

    private Long userId;

    private String email;

    private String avatar;

    private String role;
}
