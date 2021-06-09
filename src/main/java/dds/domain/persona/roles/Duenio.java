package dds.domain.persona.roles;


import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.transaccion.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class Duenio implements RolPersona {

    List<Transaccion> permisos = new ArrayList<>();

    public Duenio() {
        this.permisos.add(new RegistrarMascota());
        this.permisos.add(new EncontreMiMascota());
    }

    @Override
    public void ejecutarTransaccion(Transaccion transaccion)  {
        if(this.permisos.stream().anyMatch(p -> p.getIdTransaccion() ==(transaccion.getIdTransaccion()))){
            transaccion.ejecutar();
        }else{
            throw new TransactionException("No posee los permisos para ejecutar la transaccion");
        }
    }



}