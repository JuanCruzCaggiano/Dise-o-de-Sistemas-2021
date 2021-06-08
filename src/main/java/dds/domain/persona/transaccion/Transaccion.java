package dds.domain.persona.transaccion;

import dds.domain.asociacion.ConfigCaracMascota;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public interface Transaccion {


    void ejecutar() ;

    int getIdTransaccion();


}
