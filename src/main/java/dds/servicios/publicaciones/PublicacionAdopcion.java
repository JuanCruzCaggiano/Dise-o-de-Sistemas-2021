package dds.servicios.publicaciones;

import java.util.HashMap;

public class PublicacionAdopcion {

    private String idPublicacion;
    private String idMascota;
    private String idDueño;
    private HashMap<String, Object> preguntas = new HashMap <String, Object> ();

    public PublicacionAdopcion(String idMascota, String idDueño, HashMap<String, Object> preguntas) { //esti va a responderse desde el front con getpreguntas

        this.idMascota = idMascota;
        this.idDueño = idDueño;
        this.preguntas = preguntas; //se obtiene de la asociacion con el metodo getPreguntas
    }

    public void responderPregunta(String key, String value){ //no se si es necesario ya que las preguntas se pasan en el constr
        preguntas.put(key,value);
    }
    public HashMap<String, Object> getPreguntas() {
        return preguntas;
    }

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getIdDueño() {
        return idDueño;
    }
}
