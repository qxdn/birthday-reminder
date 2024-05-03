package org.qxdn.birthdayreminder.task;

import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.services.CharacterService;
import org.qxdn.birthdayreminder.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReminderTask {

    @Autowired
    private CharacterService characterService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void remind() {
        Date now = DateUtils.now();
        List<Character> characters = characterService.searchCharactersBirthDay(now);
    }
}
