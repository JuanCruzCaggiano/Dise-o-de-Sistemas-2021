package dds.domain.seguridad.usuario;

import dds.domain.asociacion.Configuracion;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Administrador extends Usuario implements Configuracion {

    private final List<Configuracion> configuracion;


    public Administrador(String userName,String passWord,List<Configuracion> config) throws NoSuchAlgorithmException {
        super(userName,passWord);
        this.configuracion = config;

    }


    @Override
    public void configurar() {

    }
}
