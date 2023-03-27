package com.ilzya.sett.torrentcomponent.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Magnets {

    /**
     * 站点
     */
    String site;

    /**
     * 总页数
     */
    Integer page;

    /**
     * 每页数量
     */
    Integer count;

    /**
     * 磁力信息
     */
    List<Magnet> magnet;


}
