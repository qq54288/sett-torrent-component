package com.ilzya.sett.torrentcomponent.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * aop日志
 *
 * @author sayokey
 * @date 2023/03/28
 */
@Slf4j
@Aspect
@Component
public class LogAop {

    @Autowired
    HttpServletRequest request;

    /**
     * 换行符
     */
    private final static String LINE_SEPARATOR = System.lineSeparator();

    @Pointcut("execution(public * com.ilzya.sett.torrentcomponent.controller..*.*(..))")
    public void apiPointCut() {
    }

    @Before("apiPointCut()")
    public void beforeReturning(JoinPoint joinPoint) {
        //开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印相关请求参数
        log.info("接收请求");
        log.info("=============== Start ===============");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", joinPoint.getArgs());
    }

    @After("apiPointCut()")
    public void doAfter(){
        log.info("=============== End ===============");
        log.info("结束请求");
    }


}
