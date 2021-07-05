package dds.domain.persona.transaccion;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.servicios.publicaciones.PublicacionQuieroAdoptar;

import java.util.HashMap;

public class QuieroAdoptar implements Transaccion {
    final  int idTransaccion = 1;
    String idPersona;
    private HashMap<String, Object> respuestas = new HashMap <String, Object> ();

    public QuieroAdoptar(String idPersona, HashMap<String, Object> respuestas) {
        this.idPersona = idPersona;
        this.respuestas = respuestas;
    }

    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public QuieroAdoptar() {

    }

    @Override
    public void ejecutar()  {
        PublicacionQuieroAdoptar publi = new PublicacionQuieroAdoptar(idPersona,respuestas);
        System.out.print("AAA");
        Asociacion asoc = RepositorioAsociaciones.getRepositorio().getAsociacion(RepositorioUsuarios.getRepositorio().getIDAsocXIdPersona(idPersona));
        asoc.getPublicador().agregarPublicacionQuieroAdoptar(publi);
    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
