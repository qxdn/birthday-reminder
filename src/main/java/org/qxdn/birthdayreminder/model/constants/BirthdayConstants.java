package org.qxdn.birthdayreminder.model.constants;

public class BirthdayConstants {

    public static final String CHARACTER_OTHER_NAME_SPLIT = "|";

    public static final String JWT_SECRET = "qxdn-birthday-reminder";

    public static final String JWT_ISSUER = "qxdn";

    public static final String JWT_HEADER = "Authorization";

    public static final Integer PASSWORD_SALT = 10;


    /**
     * 提醒服务时间 1点
     */
    public static final String REMINDER_JOB_TRIGGER_CRON = "0 0 1 * * ?";


    public static final String REMINDER_JOB_TRIGGER_NAME = "reminderJobTrigger";

    public static final String REMINDER_JOB_NAME = "reminderJob";

    public static final String REMINDER_JOB_GROUP = "reminderGroup";

    public static final String REMINDER_JOB_TRIGGER_GROUP = "reminderGroup";

}
