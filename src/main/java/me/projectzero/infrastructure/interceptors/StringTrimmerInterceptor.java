package me.projectzero.infrastructure.interceptors;

import me.projectzero.infrastructure.helpers.ApplicationHelper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
                Method setter = setterOfTheField(object, getter.getName().substring(3), String.class);
                if (setter != null && getter.getReturnType() == String.class && isGetter(getter)) {
                    Object value = invokeMethod(getter, object);
                    if (value == null) continue;
                    invokeMethod(setter, object, value.toString().trim());
                }
            }
        }
        return pjp.proceed(args);
    }

    private boolean isGetter(Method method) {
        return method.getParameterCount() == 0 && Modifier.isPublic(method.getModifiers()) && method.getName().startsWith("get");
    }

    private Method setterOfTheField(Object object, String fieldName, Class<?> fieldType) {
        try {
            return object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(fieldName), fieldType);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private Object invokeMethod(Method method, Object object, Object... args) {
        try {
            return args == null ? method.invoke(object) : method.invoke(object, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            return null;
        }
    }

}
