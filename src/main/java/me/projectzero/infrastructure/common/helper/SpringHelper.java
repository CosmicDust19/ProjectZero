package me.projectzero.infrastructure.common.helper;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringHelper implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;
    private static final Map<Class<?>, Object> cache = new HashMap<>();
    private static final List<Class<?>> cachedTypes = List.of(MessageSource.class);

    @Override
    public synchronized void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        if (SpringHelper.applicationContext == null) {
            SpringHelper.applicationContext = applicationContext;
            cachedTypes.forEach(type -> cache.put(type, applicationContext.getBean(type)));
        }
    }

    /**
     * @param type Type of bean to be wanted.
     * @return Spring bean instance of type parameter type without need of dependency injection.
     * @implNote Using this method can lead to multiple unexpected and hard to debug issues as it does not follow the regular Spring Framework concepts.
     * You should always try to refactor your code, so you do not need this approach.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(@NonNull Class<T> type) {
        Object cached = cache.get(type);
        if (cached != null) return (T) cached;
        return applicationContext.getBean(type);
    }

}