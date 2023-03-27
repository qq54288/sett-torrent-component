package com.ilzya.sett.torrentcomponent.exception;

import com.ilzya.sett.torrentcomponent.domain.AjaxResult;
import com.ilzya.sett.torrentcomponent.enums.ResultEnum;
import com.ilzya.sett.torrentcomponent.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle{

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public AjaxResult bizExceptionHandler(HttpServletRequest req, ServiceException e){
        log.error("发生业务异常：",e);
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public AjaxResult exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常:",e);
        return ResultUtil.error(500,e.getMessage());
    }


}
