package me.projectzero.infrastructure.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Pointcuts {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void insideController() {
    }

    @Pointcut("execution(@(@org.springframework.web.bind.annotation.RequestMapping *) * *(..))")
    public void requestMapping() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("insideController() && requestMapping() && publicMethod()")
    public void endpoint() {
    }

}
