package me.projectzero.infrastructure.common.util.reflection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;

@Slf4j
@UtilityClass
public class MethodUtils {

    public Optional<Object> tryInvoke(Method method, Object object, Object... args) {
        if (method == null || object == null) return Optional.empty();
        method.setAccessible(true);
        try {
            return Optional.ofNullable(method.invoke(object, args));
        } catch (Exception exception) {
            log.warn(exception.getMessage());
            return Optional.empty();
        }
    }

    public <T> Optional<T> tryInvoke(Method method, Object object, Class<T> type, Object... args) {
        if (method == null || object == null || type == null) return Optional.empty();
        return MethodUtils.tryInvoke(method, object, args).map(value -> {
            try {
                return type.cast(value);
            } catch (Exception exception) {
                log.warn(exception.getMessage());
                return null;
            }
        });
    }

    public boolean isGetter(Method method) {
        return method.getParameterCount() == 0 && Modifier.isPublic(method.getModifiers()) && method.getName().startsWith("get") && !method.getReturnType().equals(Void.TYPE);
    }

    public boolean isSetter(Method method) {
        return method.getParameterCount() == 1 && Modifier.isPublic(method.getModifiers()) && method.getName().startsWith("set") && method.getReturnType().equals(Void.TYPE);
    }

}
