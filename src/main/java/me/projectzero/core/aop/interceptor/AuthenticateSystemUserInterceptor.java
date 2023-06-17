package me.projectzero.core.aop.interceptor;

import me.projectzero.core.aop.annotation.AuthenticateSystemOperation;
import me.projectzero.core.common.util.reflection.JoinPointUtils;
import me.projectzero.core.common.util.security.AuthenticationUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Aspect
@Component
public class AuthenticateSystemUserInterceptor {

    @Around("@annotation(me.projectzero.core.aop.annotation.AuthenticateSystemOperation)")
    public Object authenticateSystemUser(ProceedingJoinPoint pjp) throws Throwable {
        String operation = JoinPointUtils.getMethod(pjp).getAnnotation(AuthenticateSystemOperation.class).value();
        AuthenticationUtils.setAuthentication(new User("automatic-system-operation/" + operation, "system-password", Collections.emptyList()));
        Object object = pjp.proceed();
        SecurityContextHolder.clearContext();
        return object;
    }

}
