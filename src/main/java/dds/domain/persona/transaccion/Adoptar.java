package dds.domain.persona.transaccion;

import dds.domain.asociacion.ConfigCaracMascota;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class Adoptar implements Transaccion {
    final  int idTransaccion = 1;
    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public Adoptar() {
    }

    @Override
    public void ejecutar()  {
        //TODO ADOPTAR
    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
