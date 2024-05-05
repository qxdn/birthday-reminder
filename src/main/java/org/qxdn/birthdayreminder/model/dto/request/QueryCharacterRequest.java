package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryCharacterRequest extends PageRequest {

    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色原名
     */
    private String originName;

    /**
     * 出生年
     */
    private Integer birthYear;

    /**
     * 出生月
     */
    private Integer birthMonth;

    /**
     * 出生日
     */
    private Integer birthDay;
}
