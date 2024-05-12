package org.qxdn.birthdayreminder.model.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
