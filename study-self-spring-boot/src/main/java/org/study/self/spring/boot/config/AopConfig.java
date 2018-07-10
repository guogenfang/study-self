package org.study.self.spring.boot.config;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class AopConfig {
	private Logger logger = LoggerFactory.getLogger(AopConfig.class);
	
    @Pointcut("execution(* org.study.self.spring.boot.controller.*.*(..))")  
    public void controllerPointcut(){}
    
    @Pointcut("execution(* org.study.self.spring.boot.service.*.*(..))")  
    public void servicePointcut(){}
    
    @Before("controllerPointcut()")
    public void controllerBefore(JoinPoint point) {
    	MethodSignature signature = (MethodSignature) point.getSignature();
    	Method method = signature.getMethod();
    	logger.info("controller pointcut before method name is {}-------",method.getName());
    }
    
    @Around("controllerPointcut()")
    public Object controllerAround(ProceedingJoinPoint pjp) throws Throwable{
    	Object object = null;
    	MethodSignature signature = (MethodSignature) pjp.getSignature();
    	Method method = signature.getMethod();
    	logger.info("controller pointcut Around begin method:{}", method.getName());
    	object = pjp.proceed();
    	logger.info("controller pointcut Around end method:{}", method.getName());
    	return object;
    }
    
    @After("controllerPointcut()")
    public void controllerAfter(JoinPoint pjp) throws Throwable{
    	MethodSignature signature = (MethodSignature) pjp.getSignature();
    	Method method = signature.getMethod();
    	logger.info("controller pointcut After method:{}", method.getName());
    }
    
    @Around("servicePointcut()")
    public Object serviceInterceptor(ProceedingJoinPoint pjp) throws Throwable{
    	MethodSignature signature = (MethodSignature) pjp.getSignature();
    	Method method = signature.getMethod();
    	logger.info("service interceptor method:{}", method.getName());
    	return pjp.proceed();
    }
    
}
