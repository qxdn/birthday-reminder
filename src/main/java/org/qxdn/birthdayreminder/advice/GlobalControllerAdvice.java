package org.qxdn.birthdayreminder.advice;

import lombok.extern.slf4j.Slf4j;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;
import org.qxdn.birthdayreminder.model.exception.BirthdayException;
import org.qxdn.birthdayreminder.utils.LogUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = BirthdayException.class)
    public BaseResponse<Object> handleException(BirthdayException exception){
        String errorCode = exception.getCode();
        String errorMessage = exception.getMessage();
        ErrorEnum errorEnum = ErrorEnum.getByCode(errorCode);
        switch (errorEnum){
            case CHECK_FAIL-> LogUtils.info(log,"校验失败:{}",errorMessage);
            case NO_PERMISSION-> LogUtils.info(log,"无权限:{}",errorMessage);
            case null -> LogUtils.error(log,"出现未知异常ex={}",exception);
            default-> LogUtils.error(log,"出现异常ex=",exception);
        }
        return new BaseResponse<>(exception.getCode(),exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse<Object> handleException(Exception exception){
        LogUtils.error(log,"出现未知异常ex=",exception);
        return new BaseResponse<>(ErrorEnum.FAIL,exception.getMessage());
    }
}
