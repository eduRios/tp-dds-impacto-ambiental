package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.exception.PasswordException;

public abstract class ValidatePassword {
    public ValidatePassword() {
    }

    public void validar(String password)  throws PasswordException {
        validarLongMin(password);
        validarContraseñaDebil(password);
        validarCaracteresRepetidos(password);
    }

    abstract void validarContraseñaDebil(String password);
    abstract void validarLongMin(String password);
    abstract void validarCaracteresRepetidos(String password);
}
