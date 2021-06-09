package dds.domain.persona.transaccion;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.domain.persona.Persona;
import dds.domain.persona.roles.Voluntario;
import dds.servicios.publicaciones.PublicacionMascota;
import dds.servicios.publicaciones.Publicador;

import javax.mail.MessagingException;

public class ValidarPublicacion implements Transaccion {
    final  int idTransaccion = 8;
    String idPublicacion;

    public ValidarPublicacion(){

    }

    public ValidarPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    @Override
    public void ejecutar()  {
        Asociacion asoc = RepositorioAsociaciones.getRepositorio().getAsociacion(RepositorioAsociaciones.getRepositorio().getIDAsocXIdPublicacion(idPublicacion));
        Publicador publicador= asoc.getPublicador();
        PublicacionMascota publicacion = publicador.getPendienteXId(idPublicacion);
        publicador.aprobarPublicacion(publicacion);
    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
