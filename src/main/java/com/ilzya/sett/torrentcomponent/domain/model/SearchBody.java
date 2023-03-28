package com.ilzya.sett.torrentcomponent.domain.model;

import lombok.Data;

/**
 * 搜索体
 *
 * @author sayokey
 * @date 2023/03/28
 */
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
