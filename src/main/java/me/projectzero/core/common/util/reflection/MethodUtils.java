package me.projectzero.core.common.util.reflection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
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

    public List<Object> getAnnotatedArgs(Method method, Object[] args, Class<? extends Annotation> annotation) {
        if (method == null || annotation == null || args == null) return new ArrayList<>();
        List<Object> annotated = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        int length = Math.min(parameters.length, args.length);
        for (int i = 0; i < length; i++) {
            if (parameters[i].isAnnotationPresent(annotation)) {
                annotated.add(args[i]);
            }
        }
        return annotated;
    }

}
