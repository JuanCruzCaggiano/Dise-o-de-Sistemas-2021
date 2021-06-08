package dds.domain.persona.transaccion;

import dds.db.RepositorioAsociaciones;
import dds.domain.asociacion.Asociacion;
import dds.servicios.publicaciones.PublicacionMascota;
import dds.servicios.publicaciones.Publicador;

import javax.mail.MessagingException;

public class RechazarPublicacion implements Transaccion {
    final  int idTransaccion = 6;
    String idPublicacion;

    public RechazarPublicacion(){

    }

    public RechazarPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    @Override
    public void ejecutar()  {
        Asociacion asoc = RepositorioAsociaciones.getRepositorio().getAsociacion(RepositorioAsociaciones.getRepositorio().getIDAsocXIdPublicacion(idPublicacion));
        Publicador publicador= asoc.getPublicador();
        PublicacionMascota publicacion = publicador.getPendienteXId(idPublicacion);
        publicador.rechazarPublicacion(publicacion);
    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
