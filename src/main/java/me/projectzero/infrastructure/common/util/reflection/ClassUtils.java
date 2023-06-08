package me.projectzero.infrastructure.common.util.reflection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

@Slf4j
@UtilityClass
public class ClassUtils {

    public Optional<Method> getMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        if (clazz == null || name == null) return Optional.empty();
        try {
            return Optional.of(clazz.getDeclaredMethod(name, parameterTypes));
        } catch (Exception exception) {
            log.warn(exception.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Field> getField(Class<?> clazz, String name) {
        if (clazz == null || name == null) return Optional.empty();
        try {
            return Optional.of(clazz.getDeclaredField(name));
        } catch (Exception exception) {
            log.warn(exception.getMessage());
            return Optional.empty();
        }
    }

}
