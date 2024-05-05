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
    private String name;

}