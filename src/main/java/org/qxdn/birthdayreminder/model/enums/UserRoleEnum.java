package org.qxdn.birthdayreminder.model.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum UserRoleEnum {

    ADMIN("ADMIN", "管理员"),
    USER("USER", "用户")
    ;
    private final String role;
    private final String description;

    UserRoleEnum(String role, String description) {
        this.role = role;
        this.description = description;
    }

    public static UserRoleEnum getByRole(String role) {
        if (Objects.isNull(role)) {
            return null;
        }
        role = role.toUpperCase();
        for (UserRoleEnum value : UserRoleEnum.values()) {
            if (value.getRole().equals(role)) {
                return value;
            }
        }
        return null;
    }
}
