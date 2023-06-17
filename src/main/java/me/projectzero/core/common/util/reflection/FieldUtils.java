package me.projectzero.core.common.util.reflection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@UtilityClass
public class FieldUtils {

    /**
     * @param field  The field inside the object
     * @param object The object containing the field
     * @return An {@link Optional} containing the value or if the value is null
     * or the field was not found or any {@link Exception} was thrown, then an empty {@link Optional}
     */
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

    /**
     * @param field  The field inside the object
     * @param object The object containing the field
     * @param type   The type of the field to be cast
     * @return An {@link Optional} containing the value or if the value is null
     * or the field cannot be cast to this type or the field was not found or any {@link Exception} was thrown, then an empty {@link Optional}
     */
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

    /**
     * Sets the field to the given {@code value}.
     *
     * @param field  The field inside the object
     * @param object The object containing the field
     * @return The success status
     * @implSpec The {@code value} must be in the correct type.
     */
    public boolean setValue(Field field, Object object, Object value) {
        if (object == null || field == null) return false;
        field.setAccessible(true);
        try {
            field.set(object, value);
            return true;
        } catch (Exception exception) {
            log.warn(exception.getMessage());
            return false;
        }
    }

    /**
     * Sets the field to the returning value of the {@code transformation}.
     *
     * @param field          The field inside the object
     * @param object         The object containing the field
     * @param transformation A transformation function from the old value to the new one
     * @implSpec The returning {@code value} of the {@code transformation} must be in the correct type.
     */
    public boolean setValue(Field field, Object object, Function<Optional<Object>, Object> transformation) {
        if (object == null || field == null || transformation == null) return false;
        field.setAccessible(true);
        try {
            field.set(object, transformation.apply(Optional.ofNullable(field.get(object))));
            return true;
        } catch (Exception exception) {
            log.warn(exception.getMessage());
            return false;
        }
    }

}
