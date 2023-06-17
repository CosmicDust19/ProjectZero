package me.projectzero.service.common.validation.validators;

import jakarta.validation.ConstraintValidatorContext;
import me.projectzero.core.common.util.TcknUtils;
import me.projectzero.service.common.validation.annotations.Tckn;
import me.projectzero.service.common.validation.common.AbstractConstraintValidator;

public class TCKNLongValidator extends AbstractConstraintValidator<Tckn, Long> {

    @Override
    public boolean isValid(Long tckn, ConstraintValidatorContext constraintValidatorContext) {
        if (tckn == null) return true;
        return TcknUtils.isValid(tckn);
    }

}
