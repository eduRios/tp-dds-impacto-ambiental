package com.dds.tpimpactoambiental.service;

abstract class ValidatePassword {
    public void validar(String password) {

    }

    abstract void validarContraseñaDebil(String password);
    abstract void validarLongMin(String password);
    abstract void validarRotacion(String password);
}
