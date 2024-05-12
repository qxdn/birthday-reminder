package org.qxdn.birthdayreminder.model.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.qxdn.birthdayreminder.model.enums.UserRoleEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseModel{

    private String username;

    /**
     * 邮箱
     */
    private String email;

    private String avatar;

    private String password;

    private UserRoleEnum role;

}
