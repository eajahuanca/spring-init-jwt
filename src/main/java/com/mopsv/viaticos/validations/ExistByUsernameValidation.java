package com.mopsv.viaticos.validations;

import com.mopsv.viaticos.services.UsuarioService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistByUsernameValidation implements ConstraintValidator<ExistByUsername, String> {

    @Autowired
    private UsuarioService usuarioService;

    public ExistByUsernameValidation(){}

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (usuarioService == null) {
            return true;
        }
        return !usuarioService.existsByUsername(username);
    }
}
