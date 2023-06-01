package me.projectzero.infrastructure.extensions.java.lang.reflect.Method;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MethodExtensions {

    public static List<Object> getAnnotatedArgs(@This Method method, Object[] args, Class<? extends Annotation> annotation) {
        List<Object> result = new ArrayList<>();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            for (Annotation current : annotations) {
                if (current.annotationType() == annotation) {
                    result.add(args[i]);
                }
            }
        }
        return result;
    }

    public static Object tryInvoke(@This Method method, Object object, Object... args) {
        if (method == null || object == null) return null;
        try {
            return method.invoke(object, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            return null;
        }
    }

    public static <T> T tryInvoke(@This Method method, Object object, Class<T> type, Object... args) {
        if (method == null || object == null || type == null) return null;
        Object value = method.tryInvoke(object, args);
        return type.cast(value);
    }

    public static boolean isGetterMethod(@This Method method) {
        return method.getParameterCount() == 0 && Modifier.isPublic(method.getModifiers()) && method.getName().startsWith("get") && !method.getReturnType().equals(Void.TYPE);
    }

}
