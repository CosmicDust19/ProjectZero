package me.projectzero.core.aop.annotation;

import me.projectzero.domain.common.valueobjects.HibernateFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface EnableHibernateFilter {

    /**
     * Filter class which has filter properties
     */
    Class<? extends HibernateFilter.Nested> filter();

    /**
     * Filter parameters' values
     */
    String[] values() default {};

    /**
     * Should the filter disabled after the annotated method finished?
     */
    boolean disableAfter() default false;

}
