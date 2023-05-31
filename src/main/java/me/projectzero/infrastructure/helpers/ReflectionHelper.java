package me.projectzero.infrastructure.helpers;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ReflectionHelper {

    public List<Object> getArgsByAnnotation(Parameter[] params, Object[] args, Class<? extends Annotation> searchAnnotation) {
        List<Object> annotatedArgs = new ArrayList<>();
        for (int i = 0; i < args.length; i++)
            if (params[i].isAnnotationPresent(searchAnnotation))
                annotatedArgs.add(args[i]);
        return annotatedArgs;
    }

    public <T extends Annotation> T getMethodAnnotation(Method method, Class<T> annotation) {
        return method.getAnnotation(annotation);
    }

    @SneakyThrows
    public <RT, OT> RT invokeMethod(OT object, Method method, Class<RT> returnType, Object[] paramValues) {
        return returnType.cast(method.invoke(object, paramValues));
    }

    @SneakyThrows
    public <RT, OT> RT invokeMethod(OT object, String methodName, Class<RT> returnType, Class<?>[] paramTypes, Object[] paramValues) {
        return invokeMethod(object, getMethod(object.getClass(), methodName, paramTypes), returnType, paramValues);
    }

    @SneakyThrows
    public Method getMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) {
        return clazz.getMethod(methodName, paramTypes);
    }

}
