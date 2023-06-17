package me.projectzero.core.aop.interceptor;

import me.projectzero.core.aop.annotation.Validate;
import me.projectzero.core.common.helper.ValidatorHelper;
import me.projectzero.core.common.util.reflection.JoinPointUtils;
import me.projectzero.core.common.util.reflection.MethodUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Aspect
@Component
public class ValidationInterceptor {

    private final Consumer<Object> validate;

    public ValidationInterceptor(ValidatorHelper validatorHelper) {
        this.validate = validatorHelper::validate;
    }

    @Before("execution(public * *(.., @me.projectzero.core.aop.annotation.Validate (*), ..))")
    public void validate(final JoinPoint jp) {
        MethodUtils.getAnnotatedArgs(JoinPointUtils.getMethod(jp), jp.getArgs(), Validate.class).forEach(validate);
    }

}
