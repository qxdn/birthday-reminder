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
        LogUtils.error(log,"出现异常ex=",exception);
        return new BaseResponse<>(exception.getCode(),exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse<Object> handleException(Exception exception){
        LogUtils.error(log,"出现异常ex=",exception);
        return new BaseResponse<>(ErrorEnum.FAIL,exception.getMessage());
    }
}
