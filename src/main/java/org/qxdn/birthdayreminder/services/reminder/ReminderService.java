package org.qxdn.birthdayreminder.services.reminder;

import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.model.model.content.ReminderContent;

import java.util.List;

public interface ReminderService {

    void remind(List<Character> characters, ReminderContent content);
}
