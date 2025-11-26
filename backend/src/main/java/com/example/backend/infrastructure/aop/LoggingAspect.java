package com.example.backend.infrastructure.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example..*Controller.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        System.out.println("====== [AOP Log] " + className + "." + methodName + "() が呼ばれました ======");
    }
}
