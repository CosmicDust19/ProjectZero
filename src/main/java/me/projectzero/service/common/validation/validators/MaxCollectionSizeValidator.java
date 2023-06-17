package me.projectzero.service.common.validation.validators;

import jakarta.validation.ConstraintValidatorContext;
import me.projectzero.service.common.validation.annotations.MaxCollectionSize;
import me.projectzero.service.common.validation.common.AbstractConstraintValidator;

import java.util.Collection;

public class MaxCollectionSizeValidator extends AbstractConstraintValidator<MaxCollectionSize, Collection<?>> {

    @Override
    public boolean isValid(Collection<?> values, ConstraintValidatorContext context) {
        if (values == null) return true;
        return values.size() <= constraint.value();
    }

}