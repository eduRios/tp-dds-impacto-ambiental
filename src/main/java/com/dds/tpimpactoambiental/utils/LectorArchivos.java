package com.dds.tpimpactoambiental.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class LectorArchivos {

    private URL urlArchivo;

    public LectorArchivos(URL urlArchivo){
        this.urlArchivo = urlArchivo;
    }

    public List<String> devolverContenidoComoListaDeStrings(){
        String contenidoArchivo = "";

        try{
            contenidoArchivo = Resources.toString(urlArchivo, Charsets.UTF_8);
            contenidoArchivo = contenidoArchivo.replace("\n", ",");
            contenidoArchivo = contenidoArchivo.replace("\r", "");
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }

        return Arrays.asList(contenidoArchivo.split(","));
    }

}
