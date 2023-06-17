package me.projectzero.core.common.util.reflection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Optional;

@Slf4j
@UtilityClass
public class ConstructorUtils {

    public <T> Optional<T> getInstance(Constructor<T> constructor, Object... args) {
        if (constructor == null || args == null) return Optional.empty();
        constructor.setAccessible(true);
        try {
            return Optional.of(constructor.newInstance(args));
        } catch (Exception exception) {
            log.warn(exception.getMessage());
            return Optional.empty();
        }
    }

}
