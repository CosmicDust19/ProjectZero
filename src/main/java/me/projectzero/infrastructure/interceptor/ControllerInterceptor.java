package me.projectzero.infrastructure.interceptor;

import lombok.extern.slf4j.Slf4j;
import me.projectzero.infrastructure.common.util.ApplicationInfo;
import me.projectzero.infrastructure.common.util.reflection.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Field;

@Aspect
@Slf4j
public class ControllerInterceptor {

    @Around("me.projectzero.infrastructure.interceptor.Pointcuts.endpoint()")
    public Object trim(final ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object object : args) {
            if (object == null || !object.getClass().getPackageName().startsWith(ApplicationInfo.PACKAGE_NAME)) continue;
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.getType() == String.class) {
                    FieldUtils.getValue(field, object).ifPresent(value -> FieldUtils.setValue(field, object, value.toString().trim()));
                }
            }
        }
        return pjp.proceed(args);
    }

    @Around("me.projectzero.infrastructure.interceptor.Pointcuts.endpoint()")
    public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object object = pjp.proceed();
        long end = (System.nanoTime() - start) / 1000000;
        String logMessage = "Request completed in " + end + " milliseconds: " + pjp.toShortString();
        if (end < 500)
            log.info(logMessage);
        else if (end < 2000)
            log.warn(logMessage);
        else
            log.error(logMessage);
        return object;
    }

}
