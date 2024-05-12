package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryUserRequest extends PageRequest {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;


    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色
     */
    private String role;
}
