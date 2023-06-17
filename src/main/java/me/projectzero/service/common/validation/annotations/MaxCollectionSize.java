package me.projectzero.service.common.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import me.projectzero.core.common.enumeration.ErrorCode;
import me.projectzero.service.common.validation.validators.MaxCollectionSizeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MaxCollectionSizeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxCollectionSize {

    int value();

    String message() default ErrorCode.Validation.MAX_COLLECTION_SIZE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
