package com.capstone.webserver.aspects;

import com.capstone.webserver.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
@Profile({"dev", "test"})
public class LoggingAspect {

    // 적용 범위 설정
    @Pointcut("execution(* com.capstone.webserver..api.*.*(..))")
    private void api() {}

    @Before("api()")
    public void beforeLog(JoinPoint joinPoint) throws NoSuchMethodException {

        Method method = getMethod(joinPoint);
        log.info("[Before] Method name: " + method.getName());

        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();

        for (Parameter parameter : parameters) {
            log.info("\nparameter name: " + parameter.getName());
            log.info("\nparameter type: " + parameter.getType().getSimpleName());
        }
    }
    @AfterReturning(pointcut = "api()", returning = "result")
    public void afterReturningLog(JoinPoint joinPoint, Object result) throws NoSuchMethodException {

        Method method = getMethod(joinPoint);
        log.info("[AfterReturning] Method name: " + method.getName());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("\nparameter value: " + arg);
        }


        if (result == null) return;
        log.info("\t return: " + result);
    }

    @AfterThrowing(pointcut = "api()", throwing = "e")
    public void afterThrowingLog(JoinPoint joinPoint, CustomException e) {
        log.error("[AfterThrowing] Method name: " + joinPoint.getSignature().getName());
        log.error("\nExceptionCode: " + e.getExceptionCode());
        log.error("\nExceptionMessage: " + e.getMessage());
    }

    private Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        String methodName = joinPoint.getSignature().getName();
        Class<?>[] parameterTypes = ((org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature()).getParameterTypes();
        return joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes);
    }
}
