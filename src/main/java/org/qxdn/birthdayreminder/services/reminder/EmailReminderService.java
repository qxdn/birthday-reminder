package org.qxdn.birthdayreminder.services.reminder;

import lombok.extern.slf4j.Slf4j;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.model.model.content.ReminderContent;
import org.qxdn.birthdayreminder.services.EmailService;
import org.qxdn.birthdayreminder.utils.DateUtils;
import org.qxdn.birthdayreminder.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.List;

/**
 * 邮件发送提醒
 */
@Slf4j
@Service
public class EmailReminderService implements ReminderService{

    @Autowired
    private EmailService emailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String getName() {
        return "邮件提醒服务";
    }

    @Override
    public void remind(List<Character> characters, ReminderContent content) {
        String text = createBirthdayNotifyText(characters);
        LogUtils.info(log, "Send email to {} with content: {}", content.getTo());
        emailService.sendHTMLMessage(content.getFrom(), content.getTo(), content.getSubject(), text);
    }

    public String createBirthdayNotifyText(List<Character> characters) {
        // now
        Date now = DateUtils.now();
        StringBuilder sb = new StringBuilder();
        sb.append(DateUtils.getYear(now)).append("年")
                .append(DateUtils.getMonth(now)).append("月")
                .append(DateUtils.getDay(now)).append("日");
        // context
        Context context = new Context();
        context.setVariable("date", sb.toString());
        context.setVariable("characters", characters);
        return templateEngine.process("mail", context);
    }
}
