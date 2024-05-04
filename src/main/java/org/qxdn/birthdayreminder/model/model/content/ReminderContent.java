package org.qxdn.birthdayreminder.model.model.content;

import lombok.Data;

/**
 * 提醒内容上下文
 */
@Data
public class ReminderContent {

    /****email*****/


    /**
     *  接收者
     */
    private String to;

    /**
     * 标题
     */
    private String subject;

    /**
     * 发送者
     */
    private String from;
}
