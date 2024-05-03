package org.qxdn.birthdayreminder;

import org.junit.jupiter.api.Test;
import org.qxdn.birthdayreminder.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        emailService.sendSimpleMessage("1464238196@qq.com","testmail","test content");
    }
}
