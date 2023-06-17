package me.projectzero.service.common.validation.validators;

import jakarta.validation.ConstraintValidatorContext;
import me.projectzero.service.common.validation.annotations.Unique;
import me.projectzero.service.common.validation.common.AbstractDbConstraintValidator;
import org.springframework.stereotype.Component;


@Component
public class UniqueValidator extends AbstractDbConstraintValidator<Unique, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true;
        return !dbValidationHelper.exists(constraint.entity(), constraint.fieldName(), value);
    }

}
