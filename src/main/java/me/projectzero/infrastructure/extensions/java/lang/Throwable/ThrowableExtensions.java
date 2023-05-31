package me.projectzero.infrastructure.extensions.java.lang.Throwable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThrowableExtensions {

    public static Throwable getRootCause(@This Throwable throwable) {
        if (throwable == null) return null;
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause)
            rootCause = rootCause.getCause();
        return rootCause;
    }

}
