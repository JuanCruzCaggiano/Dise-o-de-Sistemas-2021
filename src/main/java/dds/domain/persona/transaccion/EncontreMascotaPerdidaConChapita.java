package dds.domain.persona.transaccion;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioPersonas;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.domain.persona.Persona;
import dds.servicios.publicaciones.PublicacionMascota;

import java.util.ArrayList;

public class EncontreMascotaPerdidaConChapita implements Transaccion{
    final  int idTransaccion = 4;
    String idMascota;
    float latitud;
    float longitud;
    ArrayList<String> listaFotos;
    String descripcion;

    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public EncontreMascotaPerdidaConChapita(){}

    //CONSTRUCTOR PARA REALIZAR TRANSACCION
    public EncontreMascotaPerdidaConChapita(String idMascota, float latitud, float longitud, ArrayList<String> listaFotos, String descripcion) {
        this.idMascota = idMascota;
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
    }

    @Override
    public void ejecutar()  {
        PublicacionMascota publi = new PublicacionMascota(idMascota,latitud,longitud,listaFotos,descripcion);
        String idAsoc = RepositorioUsuarios.getRepositorio().getIDAsocXIdMascota(idMascota);
        Asociacion asoc = RepositorioAsociaciones.getRepositorio().getAsociacion(idAsoc);
        asoc.getPublicador().agregarPublicacionPrivada(publi);
        Persona dueño = RepositorioPersonas.getRepositorio().getPersona(RepositorioPersonas.getRepositorio().getIdPersonaXidMascota(idMascota));
        dueño.getNotificador().notificar(idMascota);

    }

    @Override
    public  int getIdTransaccion() {
        return idTransaccion;
    }
}
