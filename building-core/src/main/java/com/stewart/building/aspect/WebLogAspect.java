package com.stewart.building.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

/**
 * @author Stewart
 * @create 2021/1/6
 */
/**
 * WEB层日志切面,用来记录请求信息
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {


    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.stewart.building.mbg.controller..*.*(..))")//切入点
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        System.out.println("**************Start API Request**************");
        System.out.println("URL : " + request.getRequestURI().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("=======================================================");
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        System.out.println("=======================================================");

        System.out.println("RESPONSE : " + ret);
        System.out.println("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
        System.out.println("***************End API Request***************");

    }


}