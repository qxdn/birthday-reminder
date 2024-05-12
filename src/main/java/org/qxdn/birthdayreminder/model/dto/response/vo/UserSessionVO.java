package org.qxdn.birthdayreminder.model.dto.response.vo;

import lombok.Data;
import org.qxdn.birthdayreminder.model.enums.UserRoleEnum;

@Data
public class UserSessionVO {
    private Long id;

    private UserRoleEnum role;
}
