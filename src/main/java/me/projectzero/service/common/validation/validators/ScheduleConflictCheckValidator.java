package me.projectzero.service.common.validation.validators;

import jakarta.validation.ConstraintValidatorContext;
import me.projectzero.core.common.util.reflection.ObjectUtils;
import me.projectzero.service.common.validation.annotations.ComparableCheck;
import me.projectzero.service.common.validation.common.AbstractConstraintValidator;

public class ScheduleConflictCheckValidator extends AbstractConstraintValidator<ComparableCheck, Object> {

    @Override
    @SuppressWarnings("unchecked")
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null) return true;
        Comparable<Object> lower = ObjectUtils.getFieldValue(object, constraint.lowerField(), Comparable.class).orElse(null);
        Comparable<Object> upper = ObjectUtils.getFieldValue(object, constraint.upperField(), Comparable.class).orElse(null);
        if (lower == null || upper == null) return true;
        else return lower.compareTo(upper) < 0;
    }

}
