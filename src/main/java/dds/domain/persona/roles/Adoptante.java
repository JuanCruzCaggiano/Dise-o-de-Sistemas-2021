package dds.domain.persona.roles;

import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.transaccion.QuieroAdoptar;
import dds.domain.persona.transaccion.SolicitarAdopcion;
import dds.domain.persona.transaccion.Transaccion;
import dds.domain.persona.transaccion.DarEnAdopcion;

import java.util.ArrayList;
import java.util.List;

public class Adoptante implements RolPersona {

    List<Transaccion> permisos = new ArrayList<>();

    public Adoptante() {
        this.permisos.add(new DarEnAdopcion());
        this.permisos.add(new QuieroAdoptar());
        this.permisos.add(new SolicitarAdopcion());
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