package me.projectzero.core.common.helper;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order
@Component
@RequiredArgsConstructor
public class DbValidationHelper {

    private final SessionFactory sessionFactory;

    public boolean exists(Class<?> entity, String field, Object value) {
        return sessionFactory.getCurrentSession()
                .createQuery("select 1 from " + entity.getSimpleName() + " where " + field + " = :value", Integer.class)
                .setParameter("value", value)
                .uniqueResult() != null;
    }

}
