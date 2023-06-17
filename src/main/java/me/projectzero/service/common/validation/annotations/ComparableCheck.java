package me.projectzero.service.common.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import me.projectzero.core.common.enumeration.ErrorCode;
import me.projectzero.service.common.validation.validators.ScheduleConflictCheckValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Field types should be {@link Comparable}
 */
@Constraint(validatedBy = ScheduleConflictCheckValidator.class)
@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface ComparableCheck {

    String message() default ErrorCode.Validation.COMPARABLE_CHECK;

    String lowerField();

    String upperField();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
