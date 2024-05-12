package org.qxdn.birthdayreminder.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users",indexes = {
        @Index(name = "idx_username",columnList = "username"),
        @Index(name = "idx_email",columnList = "email"),
})
public class UserDO extends BaseDO {
    /**
     * name
     */
    @Column(name = "username",nullable = false,unique = true,updatable = false)
    private String username;

    @Column(nullable = false,unique = true)
    private String email;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;

    /**
     * 头像
     */
    private String avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDO userDO = (UserDO) o;
        return Objects.equals(username, userDO.username) && Objects.equals(email, userDO.email) && Objects.equals(password, userDO.password) && Objects.equals(avatar, userDO.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, email, password, avatar);
    }
}
