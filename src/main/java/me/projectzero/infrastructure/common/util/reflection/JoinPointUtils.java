package me.projectzero.infrastructure.common.util.reflection;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@UtilityClass
public class JoinPointUtils {

    public Method getMethod(final @NonNull JoinPoint jp) {
        return ((MethodSignature) jp.getSignature()).getMethod();
    }

}
