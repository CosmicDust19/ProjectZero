package me.projectzero.service.common.validation.validators;

import jakarta.validation.ConstraintValidatorContext;
import me.projectzero.core.common.valueobject.Regex;
import me.projectzero.core.common.util.TcknUtils;
import me.projectzero.service.common.validation.annotations.Tckn;
import me.projectzero.service.common.validation.common.AbstractConstraintValidator;


public class TCKNStringValidator extends AbstractConstraintValidator<Tckn, String> {

    @Override
    public boolean isValid(String tckn, ConstraintValidatorContext constraintValidatorContext) {
        if (tckn == null) return true;
        if (!tckn.matches(Regex.TCKN)) return false;
        return TcknUtils.isValid(Long.parseLong(tckn));
    }

}
