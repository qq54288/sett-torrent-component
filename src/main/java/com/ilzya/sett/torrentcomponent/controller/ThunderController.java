package com.ilzya.sett.torrentcomponent.controller;

import com.ilzya.sett.torrentcomponent.common.Support.ThunderSupport;
import com.ilzya.sett.torrentcomponent.common.Thunder;
import com.ilzya.sett.torrentcomponent.domain.AjaxResult;
import com.ilzya.sett.torrentcomponent.utils.ResultUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 迅雷控制器
 *
 * @author sayokey
 * @date 2023/03/28
 */
@RequestMapping("/thunder")
@RestController
@Api(value = "迅雷相关接口")
public class ThunderController {

    Thunder thunder = new ThunderSupport();

    /**
     * 添加任务
     *
     * @param magnet 磁链
     * @return {@link AjaxResult}
     */
    @RequestMapping(value = "/addtask",method = { RequestMethod.GET,RequestMethod.POST })
    @ApiOperation(value = "添加迅雷任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "magnet", value = "磁力链接", required = true, dataType = "String", defaultValue = "magnet:?xt=urn:btih:94E178476F0B72D7D5F3D7ADCAD3B63BF1E70B95")
    })
    public AjaxResult addTask(String magnet){
        thunder.addMagnetTask(magnet);
        return ResultUtil.success("调用成功");
    }

}
