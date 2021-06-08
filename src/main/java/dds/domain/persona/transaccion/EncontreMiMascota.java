package dds.domain.persona.transaccion;

import dds.domain.asociacion.ConfigCaracMascota;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;


public class EncontreMiMascota implements Transaccion {
    final  int idTransaccion = 5;

    @Override
    public void ejecutar(){

    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
