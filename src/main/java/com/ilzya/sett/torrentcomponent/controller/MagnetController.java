package com.ilzya.sett.torrentcomponent.controller;

import com.ilzya.sett.torrentcomponent.domain.AjaxResult;
import com.ilzya.sett.torrentcomponent.domain.entity.Detail;
import com.ilzya.sett.torrentcomponent.exception.ServiceException;
import com.ilzya.sett.torrentcomponent.service.MagnetService;
import com.ilzya.sett.torrentcomponent.utils.ResultUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 磁链控制器
 *
 * @author sayokey
 * @date 2023/03/28
 */
@RequestMapping("/magnet")
@RestController
@Api(value = "磁链接口")
public class MagnetController {

    @Autowired
    MagnetService magnetService;


    /**
     * 搜索
     *
     * @param keyword 关键字
     * @param nowPage 现在页面
     * @param site    网站
     * @return {@link AjaxResult}
     */
    @GetMapping("/search")
    @ApiOperation(value = "搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", required = true, dataType = "String", defaultValue = "星游记"),
            @ApiImplicitParam(name = "nowPage", value = "当前页", required = true, dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "site", value = "站点名称", required = true, dataType = "String", defaultValue = "torrentkitty")
    })
    public AjaxResult search(String keyword,
                             Integer nowPage,
                             String site){
        return ResultUtil.success(magnetService.search(keyword, nowPage, site));
    }

    /**
     * 详细信息
     *
     * @param detailUrl 详细网址
     * @param site      网站
     * @return {@link AjaxResult}
     */
    @GetMapping("/detail")
    @ApiOperation(value = "详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "detailUrl", value = "详细信息地址", required = true, dataType = "String", defaultValue = ""),
            @ApiImplicitParam(name = "site", value = "站点名称", required = true, dataType = "String", defaultValue = "torrentkitty")
    })
    public AjaxResult detail(String detailUrl,
                             String site){
        Detail detail = magnetService.detail(detailUrl, site);
        if(detail == null){
            throw new ServiceException("获取详细信息失败");
        }
        return ResultUtil.success(detail);
    }


}
