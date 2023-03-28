package com.ilzya.sett.torrentcomponent.entity;

import lombok.Data;

/**
 * 详细信息
 *
 * @author sayokey
 * @date 2023/03/28
 */
@Data
public class Detail {

    /**
     * 启用
     */
    boolean enable;

    /**
     * 弃用该属性
     */
    String domain;

    /**
     * 详细信息地址,会向中注入detailUrl
     */
    String url;

    /**
     * 磁力名
     */
    String name;

    /**
     * 上传时间
     */
    String update;

    /**
     * 大小
     */
    String size;

    /**
     * 磁力链接
     */
    String magnet;

}
