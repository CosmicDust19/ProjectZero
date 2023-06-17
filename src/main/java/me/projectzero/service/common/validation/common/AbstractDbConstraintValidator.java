package me.projectzero.service.common.validation.common;

import lombok.Setter;
import me.projectzero.core.common.helper.DbValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;

public abstract class AbstractDbConstraintValidator<C extends Annotation, T> extends AbstractConstraintValidator<C, T> {

    @Setter(onMethod = @__(@Autowired))
    protected DbValidationHelper dbValidationHelper;

}
