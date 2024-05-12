package org.qxdn.birthdayreminder.controller;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.qxdn.birthdayreminder.model.constants.BirthdayConstants;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;
import org.qxdn.birthdayreminder.model.exception.BirthdayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/scheduler")
@RestController
public class SchedulerController {

    @Autowired
    private Scheduler scheduler;

    /**
     * 手动触发通知
     * @return 返回结果
     */
    @PostMapping("/trigger")
    public BaseResponse<Void> triggerNotify() {
        JobKey jobKey = JobKey.jobKey(BirthdayConstants.REMINDER_JOB_NAME, BirthdayConstants.REMINDER_JOB_GROUP);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            throw new BirthdayException(ErrorEnum.FAIL,e);
        }
        return new BaseResponse<>();
    }
}
