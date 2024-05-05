package org.qxdn.birthdayreminder.model.model.content;

import lombok.Data;
import org.qxdn.birthdayreminder.model.model.Subscriber;

import java.util.List;

/**
 * 提醒内容上下文
 */
@Data
public class ReminderContent {

    /****email*****/


    /**
     * 标题
     */
    private String subject;

    /**
     * 订阅者
     */
    List<Subscriber> subscribers;
}
