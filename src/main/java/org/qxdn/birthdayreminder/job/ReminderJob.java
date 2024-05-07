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
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ReminderJob extends QuartzJobBean {

    private final ReminderServiceLoader reminderServiceLoader;

    private final CharacterService characterService;

    private final SubscriberService subscriberService;

    @Autowired
    public ReminderJob(ReminderServiceLoader reminderServiceLoader, CharacterService characterService, SubscriberService subscriberService) {
        this.reminderServiceLoader = reminderServiceLoader;
        this.characterService = characterService;
        this.subscriberService = subscriberService;
    }

    @Override
    protected void executeInternal(@NonNull JobExecutionContext context) throws JobExecutionException {
        Date now = DateUtils.now();
        List<Character> characters = characterService.searchCharactersBirthDay(now);
        List<ReminderService> reminderServices = reminderServiceLoader.getReminderServices();
        ReminderContent reminderContent = new ReminderContent();
        // 数据库读取
        List<Subscriber> subscribers = subscriberService.querySubscribers(true);
        LogUtils.info(log, "Send {} characters birthday remind email at {} for {} subscribers", characters.size(), DateUtils.now(), subscribers.size());
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
