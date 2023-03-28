package com.ilzya.sett.torrentcomponent.utils;

import com.ilzya.sett.torrentcomponent.domain.AjaxResult;
import com.ilzya.sett.torrentcomponent.enums.ResultEnum;

/**
 * 结果工具类
 *
 * @author sayokey
 * @date 2023/03/28
 */
public class ResultUtil {

    /**
     * 成功返回并附带数据
     * @param o
     * @return
     */
    public static AjaxResult success(Object o){
        AjaxResult result = new AjaxResult();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(o);
        return result;
    }


    /**
     * 成功返回不携带数据
     * @return
     */
    public static AjaxResult success(){
        return success(null);
    }


    /**
     * 成功返回自定义信息
     * @return
     */
    public static AjaxResult success(String message){
        AjaxResult result = new AjaxResult();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(message);
        result.setData(null);
        return result;
    }


    /**
     * 错误返回
     * @param code
     * @param message
     * @return
     */
    public static AjaxResult error(Integer code,String message){
        AjaxResult result = new AjaxResult<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }




}
