package org.qxdn.birthdayreminder.model.exception;

import lombok.Getter;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;

@Getter
public class BirthdayException extends RuntimeException {
    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误信息
     */
    private final String errorMessage;

    /**
     * 异常
     */
    private final Throwable throwable;


    public BirthdayException(String code,String errorMessage,Throwable throwable){
        super(errorMessage,throwable);
        this.code = code;
        this.errorMessage  = errorMessage;
        this.throwable = throwable;
    }


    public BirthdayException(ErrorEnum errorEnum, Throwable throwable){
        this(errorEnum.getCode(),errorEnum.getDesc(),throwable);
    }

    public BirthdayException(ErrorEnum errorEnum,String errorMessage){
        this(errorEnum.getCode(),errorMessage,null);
    }

    public BirthdayException(ErrorEnum errorEnum){
        this(errorEnum.getCode(),errorEnum.getDesc(),null);
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
