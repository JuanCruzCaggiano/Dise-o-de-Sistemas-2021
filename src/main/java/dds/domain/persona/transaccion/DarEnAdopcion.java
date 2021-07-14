package dds.domain.persona.transaccion;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.servicios.publicaciones.PublicacionAdopcion;

import java.util.HashMap;

public class DarEnAdopcion implements Transaccion {
    final  int idTransaccion = 3;
    String idMascota;
    String idDuenio;
    private HashMap<String, Object> respuestas = new HashMap <String, Object> ();


    public DarEnAdopcion() {

    }

    public DarEnAdopcion(String idMascota, String idDuenio, HashMap<String, Object> respuestas) {
        this.idMascota = idMascota;
        this.idDuenio = idDuenio;
        this.respuestas = respuestas;
    }

    @Override
    public void ejecutar(){
        String idAsoc = RepositorioUsuarios.getRepositorio().getIDAsocXIdMascota(idMascota);
        Asociacion asoc = RepositorioAsociaciones.getRepositorio().getAsociacion(idAsoc);
        PublicacionAdopcion publi = new PublicacionAdopcion(idMascota,idDuenio,respuestas);
        asoc.getPublicador().agregarPublicacionMascotaEnAdopcion(publi);

    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
