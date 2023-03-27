package com.ilzya.sett.torrentcomponent.domain;

import lombok.Data;

@Data
public class AjaxResult<T> {

    private Integer code;

    private String message;

    private T data;

}
