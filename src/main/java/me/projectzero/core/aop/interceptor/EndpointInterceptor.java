package me.projectzero.core.aop.interceptor;

import lombok.extern.slf4j.Slf4j;
import me.projectzero.core.common.util.ApplicationInfo;
import me.projectzero.core.common.util.reflection.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Aspect
@Component
public class EndpointInterceptor {

    @Around("me.projectzero.core.aop.interceptor.Pointcuts.endpoint()")
    public Object trim(final ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object object : args) {
            if (object == null || !object.getClass().getPackageName().startsWith(ApplicationInfo.BASE_PACKAGE_NAME)) continue;
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.getType() == String.class) {
                    FieldUtils.setValue(field, object, optional -> optional.map(value -> value.toString().trim()).orElse(null));
                }
            }
        }
        return pjp.proceed(args);
    }

    @Around("me.projectzero.core.aop.interceptor.Pointcuts.endpoint()")
    public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object object = pjp.proceed();
        long end = (System.nanoTime() - start) / 1000000;
        String logMessage = "Request completed in " + end + " milliseconds: " + pjp.toShortString();
        if (end < 500) {
            log.info(logMessage);
        } else if (end < 2000) {
            log.warn(logMessage);
        } else {
            log.error(logMessage);
        }
        return object;
    }

}
