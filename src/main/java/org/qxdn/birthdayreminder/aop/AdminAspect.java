package org.qxdn.birthdayreminder.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.qxdn.birthdayreminder.context.UserSessionContext;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserSessionVO;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;
import org.qxdn.birthdayreminder.model.enums.UserRoleEnum;
import org.qxdn.birthdayreminder.model.exception.BirthdayException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class AdminAspect {

    @Pointcut("@annotation(org.qxdn.birthdayreminder.annotation.RoleAdmin) && execution(* org.qxdn.birthdayreminder.controller.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        UserSessionVO userSessionVO = UserSessionContext.get();
        if (UserRoleEnum.ADMIN.equals(userSessionVO.getRole())) {
            return;
        }
        throw new BirthdayException(ErrorEnum.NO_PERMISSION);
    }
}
