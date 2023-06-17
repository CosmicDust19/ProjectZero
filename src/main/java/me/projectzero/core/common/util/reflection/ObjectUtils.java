package me.projectzero.core.common.util.reflection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@UtilityClass
public class ObjectUtils {

    public Optional<Object> getFieldValue(Object object, String fieldName) {
        if (object == null) return Optional.empty();
        return ClassUtils.getField(object.getClass(), fieldName).flatMap(field -> FieldUtils.getValue(field, object));
    }

    public <T> Optional<T> getFieldValue(Object object, String fieldName, Class<T> type) {
        if (object == null) return Optional.empty();
        return ClassUtils.getField(object.getClass(), fieldName).flatMap(field -> FieldUtils.getValue(field, object, type));
    }

    public boolean setFieldValue(Object object, String fieldName, Object value) {
        if (object == null) return false;
        return ClassUtils.getField(object.getClass(), fieldName).map(field -> FieldUtils.setValue(field, object, value)).orElse(false);
    }

    public boolean setFieldValue(Object object, String fieldName, Function<Optional<Object>, Object> transformation) {
        if (object == null) return false;
        return ClassUtils.getField(object.getClass(), fieldName).map(field -> FieldUtils.setValue(field, object, transformation)).orElse(false);
    }

}
