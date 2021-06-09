package dds.domain.persona.roles;

import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.transaccion.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;


public class Voluntario implements RolPersona {
    List<Transaccion> permisos = new ArrayList<>();

    public Voluntario() {
        this.permisos.add(new ValidarPublicacion());
        this.permisos.add(new RechazarPublicacion());
        //this.permisos.add(new GestionarPreguntasAdopcion()); TODO
    }

    @Override
    public void ejecutarTransaccion(Transaccion transaccion) {
        if(this.permisos.stream().anyMatch(p -> p.getIdTransaccion() ==(transaccion.getIdTransaccion()))){
            transaccion.ejecutar();
        }else{
            throw new TransactionException("No posee los permisos para ejecutar la transaccion");
        }
    }
}