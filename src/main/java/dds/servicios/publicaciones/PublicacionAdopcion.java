package dds.servicios.publicaciones;

import java.util.HashMap;

public class PublicacionAdopcion {
    //Necesito id publicacion?
    private String idPublicacion;
    private String idMascota;
    private String idDueño;
    private HashMap<String, Object> preguntas = new HashMap <String, Object> ();

    public PublicacionAdopcion(String idPublicacion ,String idMascota, String idDueño, HashMap<String, Object> preguntas) { //esti va a responderse desde el front con getpreguntas
        this.idPublicacion=idPublicacion;
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

    public String getIdDueño() {
        return idDueño;
    }
}
