package me.projectzero.service.common.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import me.projectzero.core.common.enumeration.ErrorCode;
import me.projectzero.service.common.validation.validators.ReferenceValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = ReferenceValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Reference.List.class)
public @interface Reference {

    Class<?> entity();

    String fieldName() default "id";

    String message() default ErrorCode.Validation.REFERENCE;

    Class<? extends Payload>[] payload() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @interface List {
        Reference[] value();
    }

}
