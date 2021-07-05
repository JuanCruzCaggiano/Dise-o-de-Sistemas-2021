package dds.servicios.publicaciones;

import java.util.HashMap;

public class PublicacionQuieroAdoptar {
    private String idPublicacion;
    private String idAdoptante;
    private HashMap<String, Object> preguntas = new HashMap <String, Object> ();

    public PublicacionQuieroAdoptar(String idAdoptante,HashMap<String, Object> preguntas) {

        this.idAdoptante = idAdoptante;
        this.preguntas = preguntas;
    }

    public HashMap<String, Object> getPreguntas() {
        return preguntas;
    }
    public void responderPregunta(String key, String value){ //no se si es necesario ya que las preguntas se pasan en el constr
        preguntas.put(key,value);
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public void setPreguntas(HashMap<String, Object> preguntas) {
        this.preguntas = preguntas;
    }

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public String getIdAdoptante() {
        return idAdoptante;
    }
}
