package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.exception.PasswordException;
import com.dds.tpimpactoambiental.utils.LectorArchivos;

import java.net.URL;
import java.util.List;

import static com.google.common.io.Resources.getResource;

public class ValidatePasswordImpl extends ValidatePassword {

    @Override
    void validarContraseñaDebil(String password) {

        List<String> contrasenias;
        URL urlArchivo = getResource("10k-most-common.txt");
        LectorArchivos lectorArchivos = new LectorArchivos(urlArchivo);

        contrasenias = lectorArchivos.devolverContenidoComoListaDeStrings();

        if (contrasenias.stream().anyMatch(contrasenia -> contrasenia.equals(password))){
            throw new PasswordException("La Password es debil");
        }
    }

    @Override
    void validarLongMin(String password){
        if(password.length()<8){
            throw new PasswordException("limite insuficiente");
        }

    }

    @Override
    void validarCaracteresRepetidos(String password) {

        for (int i = 0; i < password.length() - 1; i++) {
            int valor1 = password.charAt(i);
            int valor2 = password.charAt(i + 1);

            if (valor1 == valor2)
                throw new PasswordException("tiene caracteres repetidos");
        }
    }

}
