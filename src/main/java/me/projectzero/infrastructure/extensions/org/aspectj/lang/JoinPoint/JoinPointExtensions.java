package me.projectzero.infrastructure.extensions.org.aspectj.lang.JoinPoint;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JoinPointExtensions {

    public static Method getMethod(@This final JoinPoint jp) {
        return ((MethodSignature) jp.getSignature()).getMethod();
    }

    public static <T extends Annotation> T getMethodAnnotation(@This final JoinPoint jp, Class<T> annotation) {
        return jp.getMethod().getAnnotation(annotation);
    }

    public static List<Object> getAnnotatedArgs(@This final JoinPoint jp, Class<? extends Annotation> search) {
        List<Object> annotatedArgs = new ArrayList<>();
        Object[] allArgs = jp.getArgs();
        Annotation[][] parameterAnnotations = jp.getMethod().getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            for (Annotation annotation : annotations)
                if (annotation.annotationType() == search)
                    annotatedArgs.add(allArgs[i]);
        }
        return annotatedArgs;
    }

}
