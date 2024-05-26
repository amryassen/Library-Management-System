package com.maids.librarymanagementsystem.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("execution(* com.maids.librarymanagementsystem.book..*(..)) " +
            "|| execution(* com.maids.librarymanagementsystem.patron..*(..)) " +
            "|| execution(* com.maids.librarymanagementsystem.borrowingRecord..*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("Method called: " + methodName + " with arguments: " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "execution(* com.maids.librarymanagementsystem.book..*(..))" +
            " || execution(* com.maids.librarymanagementsystem.patron..*(..))" +
            " || execution(* com.maids.librarymanagementsystem.borrowingRecord..*(..))"
            , returning = "result")

    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Method returned: " + methodName + " with result: " + result);
    }

    @Around("execution(* com.maids.librarymanagementsystem.book..*(..)) " +
            "|| execution(* com.maids.librarymanagementsystem.patron..*(..)) " +
            "|| execution(* com.maids.librarymanagementsystem.borrowingRecord..*(..))")
    public Object logMethodPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        String methodName = joinPoint.getSignature().getName();
        log.info("Method performance: " + methodName + " executed in: " + elapsedTime + "ms");
        return result;
    }
}
