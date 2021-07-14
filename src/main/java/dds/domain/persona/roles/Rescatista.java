package dds.domain.persona.roles;

import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.transaccion.BuscarHogarDeTransito;
import dds.domain.persona.transaccion.EncontreMascotaPerdidaConChapita;
import dds.domain.persona.transaccion.EncontreMascotaPerdidaSinChapita;
import dds.domain.persona.transaccion.Transaccion;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class Rescatista implements RolPersona {

    List<Transaccion> permisos = new ArrayList<>();

    public Rescatista() {
        this.permisos.add(new EncontreMascotaPerdidaConChapita());
        this.permisos.add(new EncontreMascotaPerdidaSinChapita());
        this.permisos.add(new BuscarHogarDeTransito());
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
