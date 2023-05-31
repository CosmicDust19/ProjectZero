package me.projectzero.infrastructure.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogExecutionTimeInterceptor {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    private void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object ret = pjp.proceed();
        long end = (System.nanoTime() - start) / 1000000;
        String logMessage = "Request completed in " + end + " milliseconds: " + pjp.toShortString();
        if (end < 250) log.info(logMessage);
        else if (end > 250 && end < 1000) log.warn(logMessage);
        else log.error(logMessage);
        return ret;
    }

}
