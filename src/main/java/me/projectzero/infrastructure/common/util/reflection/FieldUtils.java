package me.projectzero.infrastructure.common.util.reflection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Optional;

@Slf4j
@UtilityClass
public class FieldUtils {

    public Optional<Object> getValue(Field field, Object object) {
        if (field == null || object == null) return Optional.empty();
        field.setAccessible(true);
        try {
            return Optional.ofNullable(field.get(object));
        } catch (Exception exception) {
            log.warn(exception.getMessage());
            return Optional.empty();
        }
    }

    public <T> Optional<T> getValue(Field field, Object object, Class<T> type) {
        if (field == null || object == null || type == null) return Optional.empty();
        return getValue(field, object).map(value -> {
            try {
                return type.cast(value);
            } catch (Exception exception) {
                log.warn(exception.getMessage());
                return null;
            }
        });
    }

    public void setValue(Field field, Object object, Object value) {
        if (object == null || field == null) return;
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (Exception exception) {
            log.warn(exception.getMessage());
        }
    }

}
