package org.qxdn.birthdayreminder.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;
import org.qxdn.birthdayreminder.model.exception.BirthdayException;
import org.qxdn.birthdayreminder.utils.LogUtils;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class WebAspect {

    @Pointcut("execution(* org.qxdn.birthdayreminder.controller.*.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            LogUtils.info(log,"请求开始,方法:{},参数:{}",joinPoint.getSignature().toShortString(),joinPoint.getArgs());
            Object result = joinPoint.proceed();
            if (result instanceof byte[]) {
                return result;
            }
            LogUtils.info(log,"请求结束,方法:{},参数:{},结果:{}",joinPoint.getSignature().toShortString(),joinPoint.getArgs(),result);
            return result;
        } catch (BirthdayException e) {
            throw e;
        } catch (Exception e) {
            throw new BirthdayException(ErrorEnum.FAIL,e);
        }
    }
}
