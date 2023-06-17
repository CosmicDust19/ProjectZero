package me.projectzero.service.common.validation.annotations;

import jakarta.validation.constraints.Pattern;
import me.projectzero.core.common.enumeration.ErrorCode;
import me.projectzero.core.common.valueobject.Regex;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = Regex.UUID, message = ErrorCode.Validation.UUID)
public @interface UUID {
}
