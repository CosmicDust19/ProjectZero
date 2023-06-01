package me.projectzero.infrastructure.interceptors;

import me.projectzero.infrastructure.helpers.ApplicationHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class StringTrimmerInterceptor {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    private void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object trim(final ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object object : args) {
            if (object == null || !object.getClass().getPackageName().startsWith(ApplicationHelper.PACKAGE_NAME)) continue;
            for (Method getter : object.getClass().getDeclaredMethods()) {
                if (getter.getReturnType() == String.class && getter.isGetterMethod()) {
                    Object value = getter.tryInvoke(object);
                    Method setter = object.getClass().getSetterMethod(getter.getName().substring(3), String.class);
                    if (setter == null || value == null) continue;
                    setter.tryInvoke(object, value.toString().trim());
                }
            }
        }
        return pjp.proceed(args);
    }

}
