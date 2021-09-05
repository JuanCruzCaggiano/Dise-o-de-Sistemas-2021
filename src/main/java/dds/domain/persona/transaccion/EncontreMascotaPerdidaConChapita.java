package dds.domain.persona.transaccion;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioPersonas;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.domain.persona.Persona;
import dds.servicios.publicaciones.PublicacionMascota;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("mascota_perdida_con")
public class EncontreMascotaPerdidaConChapita extends Transaccion{
    @Transient
    String idMascota;
    @Transient
    float latitud;
    @Transient
    float longitud;
    @Transient
    ArrayList<String> listaFotos;
    @Transient
    String descripcion;
    @Transient
    String idRescatista;

    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public EncontreMascotaPerdidaConChapita(){
//        this.idTransaccion = 4;
    }

    //CONSTRUCTOR PARA REALIZAR TRANSACCION
    public EncontreMascotaPerdidaConChapita(String idMascota, float latitud, float longitud, ArrayList<String> listaFotos, String descripcion,String idRescatista) {
//        this.idTransaccion = 4;
        this.idMascota = idMascota;
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
        this.idRescatista = idRescatista;
    }

    @Override
    public void ejecutar()  {
        PublicacionMascota publi = new PublicacionMascota(idMascota,latitud,longitud,listaFotos,descripcion,idRescatista);
        int idAsoc = RepositorioUsuarios.getRepositorio().getIDAsocXIdMascota(idMascota);
        Asociacion asoc = RepositorioAsociaciones.getRepositorio().getAsociacion(idAsoc);
        asoc.getPublicador().agregarPublicacionPrivada(publi);
        Persona duenio = RepositorioPersonas.getRepositorio().getPersona(RepositorioPersonas.getRepositorio().getIdPersonaXidMascota(idMascota));
        duenio.getNotificador().notificar(idMascota);

    }

    @Override
    public  int getIdTransaccion() {
        return idTransaccion;
    }
}
