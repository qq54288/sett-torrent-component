package com.ilzya.sett.torrentcomponent.domain.entity;

import lombok.Data;

@Data
public class Detail {

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
