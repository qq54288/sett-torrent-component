package com.ilzya.sett.torrentcomponent.domain.entity;

import lombok.Data;

@Data
public class Magnet {

    /**
     * 磁力名字
     */
    String name;

    /**
     * 磁力大小
     */
    String size;

    /**
     * 磁力上传时间
     */
    String update;

    /**
     * 磁力链接
     */
    String magnet;

    /**
     * 详细信息地址,如果磁力等信息在该地址中可以在rule.json中获取该地址,请求controller中的detail传入相关信息即可获取
     */
    String detailUrl;


}
