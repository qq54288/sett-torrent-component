package com.ilzya.sett.torrentcomponent.domain.model;

import lombok.Data;

@Data
public class SearchBody {

    /**
     * 站点
     */
    String site;

    /**
     * 关键字
     */
    String keyword;

    /**
     * 当前页数
     */
    Integer nowPage;


}
