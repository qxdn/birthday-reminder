package org.qxdn.birthdayreminder.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.model.model.Subscriber;
import org.qxdn.birthdayreminder.model.model.content.ReminderContent;
import org.qxdn.birthdayreminder.services.CharacterService;
import org.qxdn.birthdayreminder.services.SubscriberService;
import org.qxdn.birthdayreminder.services.reminder.ReminderService;
import org.qxdn.birthdayreminder.services.reminder.ReminderServiceLoader;
import org.qxdn.birthdayreminder.utils.DateUtils;
import org.qxdn.birthdayreminder.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ReminderJob extends QuartzJobBean {

    @Autowired
    private ReminderServiceLoader reminderServiceLoader;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private SubscriberService subscriberService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date now = DateUtils.now();
        List<Character> characters = characterService.searchCharactersBirthDay(now);
        List<ReminderService> reminderServices = reminderServiceLoader.getReminderServices();
        ReminderContent reminderContent = new ReminderContent();
        // TODO: 数据库读取
        List<Subscriber> subscribers = subscriberService.querySubscribers(true);
        reminderContent.setSubscribers(subscribers);
        reminderContent.setSubject("生日提醒");
        reminderServices.stream()
                .parallel()
                .forEach(reminderService -> {
                    try {
                        reminderService.remind(characters, reminderContent);
                    } catch (Exception e) {
                        LogUtils.error(log,"{}提醒失败 ex={}",reminderService.getName(),e);
                    }
                });
    }
}
