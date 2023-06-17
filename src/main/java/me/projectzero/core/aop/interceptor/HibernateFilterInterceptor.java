package me.projectzero.core.aop.interceptor;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import me.projectzero.domain.common.valueobjects.HibernateFilter;
import me.projectzero.core.aop.annotation.EnableHibernateFilter;
import me.projectzero.core.common.util.reflection.ClassUtils;
import me.projectzero.core.common.util.reflection.ConstructorUtils;
import me.projectzero.core.common.util.reflection.JoinPointUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class HibernateFilterInterceptor {

    private final EntityManager entityManager;

    private Session session;

    @Before("@annotation(me.projectzero.core.aop.annotation.EnableHibernateFilter)")
    public void enableHibernateFilter(final JoinPoint joinPoint) {
        EnableHibernateFilter annotation = JoinPointUtils.getMethod(joinPoint).getAnnotation(EnableHibernateFilter.class);
        session = entityManager.unwrap(Session.class);
        HibernateFilter.Nested properties = ClassUtils.getConstructor(annotation.filter()).flatMap(ConstructorUtils::getInstance).orElseThrow();
        Filter filter = session.enableFilter(properties.getName());
        for (int i = 0; i < properties.getParamNames().length; i++) {
            filter.setParameter(properties.getParamNames()[i], annotation.values()[i]);
        }
    }

    @After("@annotation(me.projectzero.core.aop.annotation.EnableHibernateFilter)")
    public void disableHibernateFilter(final JoinPoint joinPoint) {
        EnableHibernateFilter annotation = JoinPointUtils.getMethod(joinPoint).getAnnotation(EnableHibernateFilter.class);
        if (annotation.disableAfter())
            session.disableFilter(annotation.filter().getName());
    }

}
