package org.qxdn.birthdayreminder.config;

import org.qxdn.birthdayreminder.job.ReminderJob;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.model.model.content.CharacterContent;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.Configuration;


@RegisterReflectionForBinding({
        Character.class,
        CharacterContent.class,
        ReminderJob.class
})
@Configuration
public class ReflectConfig {
}
