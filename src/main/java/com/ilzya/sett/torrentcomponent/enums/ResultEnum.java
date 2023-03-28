package com.ilzya.sett.torrentcomponent.enums;

/**
 * 结果枚举
 *
 * @author sayokey
 * @date 2023/03/28
 */
public enum ResultEnum {

    SUCCESS(200,"success"),
    ERROR(-1,"error"),
    ;



    private Integer code;
    private String message;



    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
