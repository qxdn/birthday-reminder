package org.qxdn.birthdayreminder.model.dto.response.vo;

import lombok.Data;

import java.util.Date;

/**
 * 订阅者VO
 */
@Data
public class SubscriberVO {

    /**
     * 订阅者id
     */
    private Long id;

    /**
     * 订阅者邮箱
     */
    private String email;

    /**
     * 订阅者是否激活
     */
    private Boolean active;

    /**
     * 订阅者创建时间
     */
    private Date gmtCreate;

    /**
     * 订阅者更新时间
     */
    private Date gmtUpdate;
}
