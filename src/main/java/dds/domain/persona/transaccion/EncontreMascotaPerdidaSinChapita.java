package dds.domain.persona.transaccion;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioPersonas;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.domain.persona.Persona;
import dds.servicios.publicaciones.PublicacionMascota;

import java.util.ArrayList;

public class EncontreMascotaPerdidaSinChapita implements Transaccion{
    final  int idTransaccion = 9;
    String idMascota;
    float latitud;
    float longitud;
    ArrayList<String> listaFotos;
    String descripcion;

    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public EncontreMascotaPerdidaSinChapita(){}

    //CONSTRUCTOR PARA REALIZAR TRANSACCION
    public EncontreMascotaPerdidaSinChapita(String idMascota, float latitud, float longitud, ArrayList<String> listaFotos, String descripcion) {
        this.idMascota = idMascota;
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
    }

    @Override
    public void ejecutar()  {
        //TODO Falta implementar logica

    }

    @Override
    public  int getIdTransaccion() {
        return idTransaccion;
    }
}
