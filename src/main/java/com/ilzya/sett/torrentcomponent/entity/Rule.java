package com.ilzya.sett.torrentcomponent.entity;

import lombok.Data;

/**
 * 规则
 *
 * @author sayokey
 * @date 2023/03/28
 */
@Data
public class Rule {

    /**
     * 规则名
     */
    String site;

    /**
     * 搜索地址
     */
    String searchUrl;

    /**
     * 结果组
     */
    String group;

    /**
     * 每页数量
     */
    Integer count;

    /**
     * 结果组从几开始
     */
    Integer groupStart;

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

    /**
     * 总页数
     */
    String page;

    /**
     * 详细信息
     */
    String detailUrl;

    /**
     * 代理
     */
    Proxy proxy;

    /**
     * 详细信息
     */
    Detail detail;


}
