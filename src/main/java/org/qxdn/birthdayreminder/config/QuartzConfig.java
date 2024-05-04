package org.qxdn.birthdayreminder.config;

import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.qxdn.birthdayreminder.job.ReminderJob;
import org.qxdn.birthdayreminder.model.constants.BirthdayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Objects;

@EnableScheduling
@Configuration
public class QuartzConfig {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void init(){
        boolean notExist = false;
        try {
            // 定时作业内容
           JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(BirthdayConstants.REMINDER_JOB_NAME,BirthdayConstants.REMINDER_JOB_GROUP));
           if (Objects.isNull(jobDetail)) {
               // 创建默认作业
               jobDetail = JobBuilder.newJob(ReminderJob.class)
                       .withIdentity(BirthdayConstants.REMINDER_JOB_NAME,BirthdayConstants.REMINDER_JOB_GROUP)
                       .storeDurably()
                       .build();
                notExist = true;
           }
           // 定时触发器
           Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(BirthdayConstants.REMINDER_JOB_TRIGGER_NAME,BirthdayConstants.REMINDER_JOB_TRIGGER_GROUP));
          if (Objects.isNull(trigger)) {
              // 创建默认触发器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(BirthdayConstants.REMINDER_JOB_TRIGGER_CRON);
            trigger = TriggerBuilder.newTrigger()
                      .forJob(jobDetail)
                      .withIdentity(BirthdayConstants.REMINDER_JOB_TRIGGER_NAME,BirthdayConstants.REMINDER_JOB_TRIGGER_GROUP)
                      .withSchedule(scheduleBuilder)
                      .build();
            scheduler.scheduleJob(jobDetail,trigger);
            notExist = true;
          }
          if (notExist) {
              scheduler.rescheduleJob(TriggerKey.triggerKey("reminderJobTrigger","reminderGroup"),trigger);
          }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }


}
