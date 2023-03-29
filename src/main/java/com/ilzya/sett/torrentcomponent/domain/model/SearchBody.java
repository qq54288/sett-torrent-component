package com.ilzya.sett.torrentcomponent.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搜索体
 *
 * @author sayokey
 * @date 2023/03/28
 */
@Data
@ApiModel(value = "搜索体")
public class SearchBody {

    /**
     * 站点
     */
    @ApiModelProperty(value = "站点")
    String site;

    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字")
    String keyword;

    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页")
    Integer nowPage;


}
