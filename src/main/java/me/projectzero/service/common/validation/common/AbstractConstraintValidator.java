package me.projectzero.service.common.validation.common;

import jakarta.validation.ConstraintValidator;

import java.lang.annotation.Annotation;

public abstract class AbstractConstraintValidator<C extends Annotation, T> implements ConstraintValidator<C, T> {

    protected C constraint;

    @Override
    public void initialize(C constraint) {
        this.constraint = constraint;
        ConstraintValidator.super.initialize(constraint);
    }

}
