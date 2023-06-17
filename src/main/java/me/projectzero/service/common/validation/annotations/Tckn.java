package me.projectzero.service.common.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import me.projectzero.core.common.enumeration.ErrorCode;
import me.projectzero.service.common.validation.validators.TCKNLongValidator;
import me.projectzero.service.common.validation.validators.TCKNStringValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Turkish republic identification number
 */
@Constraint(validatedBy = {TCKNLongValidator.class, TCKNStringValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Tckn {

    String message() default ErrorCode.Validation.TCKN;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
