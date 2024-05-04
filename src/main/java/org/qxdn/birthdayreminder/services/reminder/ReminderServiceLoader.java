package org.qxdn.birthdayreminder.services.reminder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ReminderServiceLoader implements ApplicationContextAware {

    private Map<String,ReminderService> reminderServiceMap;

    public List<ReminderService> getReminderServices() {
       return new ArrayList<>(reminderServiceMap.values());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        reminderServiceMap = applicationContext.getBeansOfType(ReminderService.class);
    }
}
