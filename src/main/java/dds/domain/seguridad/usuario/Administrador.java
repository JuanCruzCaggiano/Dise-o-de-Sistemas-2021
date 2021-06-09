package dds.domain.seguridad.usuario;

import java.security.NoSuchAlgorithmException;

public class Administrador extends Usuario {




    public Administrador(String userName,String passWord) throws NoSuchAlgorithmException {
        super(userName,passWord);
    }



}
