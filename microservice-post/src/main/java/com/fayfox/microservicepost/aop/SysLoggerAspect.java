package com.fayfox.microservicepost.aop;

import com.fayfox.annotation.SysLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SysLoggerAspect {
    @Pointcut("@annotation(com.fayfox.annotation.SysLogger)")
    public void loggerPointCut() {

    }

    /**
     * 打印一下接口访问耗时
     * @param joinPoint
     * @throws Throwable
     */
    @Around("loggerPointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        joinPoint.proceed();
        System.out.println("接口方法耗时：" + (System.currentTimeMillis() - beginTime) + "ms");
    }

    /**
     * 学习测试用的，打印各种参数
     * @param joinPoint
     */
    @Before("loggerPointCut()")
    public void printSysLog(JoinPoint joinPoint) {
        System.out.println("---------------------------aop分割线---------------------------");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogger sysLogger = method.getAnnotation(SysLogger.class);
        if (sysLogger != null) {
            //注解上的描述
            System.out.println("描述：" + sysLogger.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        System.out.println("类名：" + className);
        System.out.println("方法名：" + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("请求的参数");
        for (Object o : args) {
            System.out.println(o);
        }
    }

}

