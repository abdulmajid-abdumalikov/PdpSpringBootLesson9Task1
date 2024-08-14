package com.malikov.springbootlesson9task1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.malikov.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.malikov.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.malikov.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("Exception in method: " + joinPoint.getSignature().getName() + ", Exception: " + ex.getMessage());
    }
}
