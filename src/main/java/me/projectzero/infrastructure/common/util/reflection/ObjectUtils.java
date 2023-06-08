package me.projectzero.infrastructure.common.util.reflection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@UtilityClass
public class ObjectUtils {

    public <T> Optional<T> getFieldValue(Object object, String fieldName, Class<T> type) {
        if (object == null || fieldName == null || type == null) return Optional.empty();
        return ClassUtils.getField(object.getClass(), fieldName).flatMap(field -> FieldUtils.getValue(field, object, type));
    }

    public void setFieldValue(Object object, String fieldName, Object value) {
        if (object == null || fieldName == null) return;
        ClassUtils.getField(object.getClass(), fieldName).ifPresent(field -> FieldUtils.setValue(field, object, value));
    }

}
