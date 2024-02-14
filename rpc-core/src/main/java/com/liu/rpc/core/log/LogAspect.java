package com.liu.rpc.core.log;

import com.liu.rpc.core.code.CommonSerializer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private CommonSerializer commonSerializer;

    /**
     * 定义切入点，以logAdvice注解为切入点
     */
    @Pointcut("@annotation(com.liu.rpc.core.log.LogAdvice)")
    public void logAdvice() {

    }

    /**
     * 定义切面
     */
    @Before("logAdvice()")
    public void before(JoinPoint joinPoint) {

        //打印类名
        logger.info("class name : {}", joinPoint.getClass().getClass());

        //打印方法名
        logger.info("method name is :{}",commonSerializer.serializer(joinPoint.getSignature().getName()));

        //打印入参
        logger.info("param is : {}",commonSerializer.serializer(joinPoint.getArgs()));
    }
    @After("logAdvice()")
    public void after(JoinPoint joinPoint) {

    }

    @Around("logAdvice()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        //出参
        logger.info("out param {}", commonSerializer.serializer(point.proceed().toString()));
    }

}
