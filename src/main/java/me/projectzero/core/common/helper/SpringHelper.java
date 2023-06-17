package me.projectzero.core.common.helper;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringHelper implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;
    private static final Map<Class<?>, Object> cache = new HashMap<>();

    @Override
    public synchronized void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        if (SpringHelper.applicationContext == null) {
            SpringHelper.applicationContext = applicationContext;
        }
    }

    /**
     * The purpose of this method is to fetch beans from Spring IoC container without need of dependency injection.
     *
     * @param type The type of bean to be wanted from Spring IoC container
     * @return The bean which is fetched from Spring IoC container
     * @implNote Using this method can lead to multiple unexpected and hard to debug issues as it does not follow the regular Spring Framework concepts.
     * You should always try to refactor your code, so you do not need this approach.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(@NonNull Class<T> type) {
        if (applicationContext == null) throw new IllegalStateException("Application context is not set");
        Object cached = cache.get(type);
        if (cached != null) return (T) cached;
        T bean = applicationContext.getBean(type);
        cache.put(type, bean);
        return bean;
    }

    public static String getMessage(String code, Object... args) {
        if (applicationContext == null) throw new IllegalStateException("Application context is not set");
        return applicationContext.getMessage(code, args, "No message found for the code: " + code, LocaleContextHolder.getLocale());
    }

    public static String getMessage(int code, Object... args) {
        return getMessage(String.valueOf(code), args);
    }

}