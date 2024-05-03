package org.qxdn.birthdayreminder.model.dto.response.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用户VO
 */
@Data
public class UserVO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
