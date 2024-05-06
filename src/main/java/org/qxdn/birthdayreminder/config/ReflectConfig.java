package org.qxdn.birthdayreminder.config;

import org.qxdn.birthdayreminder.model.model.content.CharacterContent;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.Configuration;


@RegisterReflectionForBinding({
        CharacterContent.class,
})
@Configuration
public class ReflectConfig {
}
