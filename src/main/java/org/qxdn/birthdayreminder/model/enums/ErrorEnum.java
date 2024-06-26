package org.qxdn.birthdayreminder.model.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    SUCCESS("0","成功"),

    FAIL("1000","失败"),

    LOGIN_FAIL("100","用户名或密码错误"),
    USER_EXIST("101","用户已存在"),
    NOT_LOGIN("102","未登录"),
    NO_PERMISSION("103","无权限"),

    CHECK_FAIL("200","校验失败"),
    ;
    private final String code;

    private final String desc;

    ErrorEnum(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ErrorEnum getByCode(String code){
        for (ErrorEnum errorEnum: ErrorEnum.values()) {
            if (errorEnum.getCode().equals(code)) {
                return  errorEnum;
            }
        }
        return null;
    }
}
