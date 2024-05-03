package org.qxdn.birthdayreminder.model.dto.response;


import lombok.Data;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;

import static org.qxdn.birthdayreminder.model.enums.ErrorEnum.SUCCESS;

/**
 * 基础响应
 */
@Data
public class BaseResponse<T> {

    /**
     * 返回数据
     */
    private T data;

    /**
     * 错误代码
     */
    private String errorCode = SUCCESS.getCode();

    /**
     * 错误信息
     */
    private String errorMessage = SUCCESS.getDesc();

    /**
     * 是否成功
     */
    private Boolean success = true;

    private Long total;

    public BaseResponse(){

    }

    public BaseResponse(T data){
        this.data = data;
    }

    public BaseResponse(T data,Long total){
        this.data = data;
        this.total = total;
    }

    public BaseResponse(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getCode();
        this.errorMessage = errorEnum.getDesc();
        this.success = SUCCESS.getCode().equals(errorEnum.getCode());
    }

    public BaseResponse(ErrorEnum errorEnum,String errorMessage) {
        this.errorCode = errorEnum.getCode();
        this.errorMessage = errorMessage;
        this.success = SUCCESS.getCode().equals(errorEnum.getCode());
    }

    public BaseResponse(String errorCode,String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.success = false;
    }
}
