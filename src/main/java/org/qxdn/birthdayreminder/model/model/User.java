package org.qxdn.birthdayreminder.model.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseModel{

    private String name;

    private String password;
}
