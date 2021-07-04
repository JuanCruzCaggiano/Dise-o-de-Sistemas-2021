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
    float latitud;
    float longitud;
    String idRescatista;
    ArrayList<String> listaFotos;
    String descripcion;

    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public EncontreMascotaPerdidaSinChapita(){}

    //CONSTRUCTOR PARA REALIZAR TRANSACCION
    public EncontreMascotaPerdidaSinChapita(float latitud, float longitud, ArrayList<String> listaFotos, String descripcion,String idRescatista) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
        this.idRescatista =idRescatista;
    }

    @Override
    public void ejecutar()  {
        PublicacionMascota publi = new PublicacionMascota(latitud,longitud,listaFotos,descripcion);
        Asociacion asoc = RepositorioAsociaciones.getRepositorio().getAsociacionMasCercana(latitud,longitud);
        publi.setIdRescatista(idRescatista);
        asoc.getPublicador().agregarPublicacionPendiente(publi);
    }

    @Override
    public  int getIdTransaccion() {
        return idTransaccion;
    }
}
