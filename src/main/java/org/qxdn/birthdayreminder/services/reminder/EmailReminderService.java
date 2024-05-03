package org.qxdn.birthdayreminder.services.reminder;

import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.model.model.content.ReminderContent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 邮件发送提醒
 */
@Service
public class EmailReminderService implements ReminderService{
    @Override
    public void remind(List<Character> characters, ReminderContent content) {

    }
}
