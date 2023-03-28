package com.ilzya.sett.torrentcomponent.exception;

import com.ilzya.sett.torrentcomponent.domain.AjaxResult;
import com.ilzya.sett.torrentcomponent.enums.ResultEnum;
import com.ilzya.sett.torrentcomponent.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author sayokey
 * @date 2023/03/28
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle{

    /**
     * 业务异常处理程序
     *
     * @param req 要求事情
     * @param e   e
     * @return {@link AjaxResult}
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public AjaxResult bizExceptionHandler(HttpServletRequest req, ServiceException e){
        log.error("发生业务异常：",e);
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

    /**
     * 异常处理程序
     *
     * @param req 要求事情
     * @param e   e
     * @return {@link AjaxResult}
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public AjaxResult exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常:",e);
        return ResultUtil.error(500,e.getMessage());
    }


}
