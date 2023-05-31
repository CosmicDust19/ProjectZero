package me.projectzero.infrastructure.extensions.java.lang.Class;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import me.projectzero.infrastructure.helpers.ApplicationHelper;

@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassExtensions {

    public static <T> T getSpringBean(@This Class<T> thisClass) {
        return ApplicationHelper.getBean(thisClass);
    }

}
