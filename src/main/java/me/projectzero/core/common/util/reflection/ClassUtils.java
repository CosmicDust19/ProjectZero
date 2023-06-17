package me.projectzero.core.common.util.reflection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import me.projectzero.core.common.util.ApplicationInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

@Slf4j
@UtilityClass
public class ClassUtils {

    /**
     * @param clazz          The class containing the method
     * @param name           The method's name
     * @param parameterTypes Method's parameter types in the correct order or empty if no parameter exists
     * @return An {@link Optional} containing the {@link Method} or if the method was not found
     * or any {@link Exception} was thrown, then an empty {@link Optional}
     */
    public Optional<Method> getMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        if (clazz == null || name == null || parameterTypes == null) return Optional.empty();
        try {
            return Optional.of(clazz.getDeclaredMethod(name, parameterTypes));
        } catch (Exception exception) {
            log.warn(exception.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param clazz The class containing the field
     * @param name  The field's name
     * @return An {@link Optional} containing the {@link Field} or if the field was not found
     * or any {@link Exception} was thrown, then an empty {@link Optional}
     */
    public Optional<Field> getField(Class<?> clazz, String name) {
        if (clazz == null || name == null) return Optional.empty();
        try {
            return Optional.of(clazz.getDeclaredField(name));
        } catch (Exception exception) {
            log.warn(exception.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param clazz          The class containing the constructor
     * @param parameterTypes Constructor's parameter types in the correct order or empty if no parameter exists
     * @return An {@link Optional} containing the {@link Constructor} or if the constructor was not found
     * or any {@link Exception} was thrown, then an empty {@link Optional}
     */
    public <T> Optional<Constructor<T>> getConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        if (clazz == null || parameterTypes == null) return Optional.empty();
        try {
            return Optional.of(clazz.getConstructor(parameterTypes));
        } catch (Exception exception) {
            log.warn(exception.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param clazz The super class
     * @return Set of all subclasses of the {@code clazz}
     */
    public Set<Class<?>> getSubClasses(Class<?> clazz) {
        return PackageUtils.getSubClassesInsidePackage(ApplicationInfo.BASE_PACKAGE_NAME, clazz);
    }

}
