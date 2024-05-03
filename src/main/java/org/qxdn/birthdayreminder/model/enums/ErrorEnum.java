package org.qxdn.birthdayreminder.model.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    SUCCESS("0","成功"),

    FAIL("100","失败"),



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
