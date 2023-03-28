package com.ilzya.sett.torrentcomponent.controller;

import com.ilzya.sett.torrentcomponent.common.Support.ThunderSupport;
import com.ilzya.sett.torrentcomponent.common.Thunder;
import com.ilzya.sett.torrentcomponent.domain.AjaxResult;
import com.ilzya.sett.torrentcomponent.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("thunder")
@RestController
public class ThunderController {

    Thunder thunder = new ThunderSupport();

    @RequestMapping("/addtask")
    public AjaxResult addTask(String magnet){
        thunder.addMagnetTask(magnet);
        return ResultUtil.success("调用成功");
    }

}
