package com.ilzya.sett.torrentcomponent.domain;

import lombok.Data;

/**
 * ajax结果
 *
 * @author sayokey
 * @date 2023/03/28
 */
@Data
public class AjaxResult<T> {


    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

}
