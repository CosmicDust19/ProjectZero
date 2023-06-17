package me.projectzero.core.common.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ThrowableUtils {

    public Throwable getRootCause(Throwable throwable) {
        if (throwable == null) return null;
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

}
