package me.projectzero.infrastructure.extensions.java.lang.Class;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import me.projectzero.infrastructure.helpers.ApplicationHelper;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassExtensions {

    public static <T> T getSpringBean(@This Class<T> clazz) {
        return ApplicationHelper.getBean(clazz);
    }

    public static Method getSetterMethod(@This Class<?> clazz, String fieldName, Class<?> fieldType) {
        try {
            return clazz.getDeclaredMethod("set" + StringUtils.capitalize(fieldName), fieldType);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}
