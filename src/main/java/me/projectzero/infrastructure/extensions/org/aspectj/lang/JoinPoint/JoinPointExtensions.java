package me.projectzero.infrastructure.extensions.org.aspectj.lang.JoinPoint;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JoinPointExtensions {

    public static Method getMethod(@This final JoinPoint jp) {
        return ((MethodSignature) jp.getSignature()).getMethod();
    }

}
