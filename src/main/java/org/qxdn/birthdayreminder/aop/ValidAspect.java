package org.qxdn.birthdayreminder.aop;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.validator.HibernateValidator;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;
import org.qxdn.birthdayreminder.model.exception.BirthdayException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Aspect
@Component
public class ValidAspect {

    private final Validator validatorFast = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory()
            .getValidator();

    @Pointcut("execution(* org.qxdn.birthdayreminder.facade..*.*(..))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void validBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            // 校验
           Set<ConstraintViolation<Object>> result = validatorFast.validate(arg);
              if (!result.isEmpty()){
                  String message = result.iterator().next().getMessage();
                  throw new BirthdayException(ErrorEnum.CHECK_FAIL,message);
              }
        }

    }
}
