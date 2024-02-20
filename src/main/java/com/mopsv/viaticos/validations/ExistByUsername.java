package com.mopsv.viaticos.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.*;

@Constraint(validatedBy = ExistByUsernameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistByUsername {
    String message() default "ya existe, favor tome otro username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
