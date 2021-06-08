package dds.domain.persona.transaccion;

import dds.domain.asociacion.ConfigCaracMascota;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class BuscarHogarDeTransito implements Transaccion {
    final  int idTransaccion = 2;
    private float lat;
    private float longitud;
    private double radio;
    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public BuscarHogarDeTransito(){
    }

    //CONSTRUCTOR PARA EJECUTAR TRANSACCION
    public BuscarHogarDeTransito(float lat, float longitud, double radio) {
        this.lat = lat;
        this.longitud = longitud;
        this.radio = radio;
    }




    //BUSCAR HOGAR DE TRANSITO
    @Override
    public void ejecutar() {
//TODO logica de buscar hogar
    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }


}
