package dds.servicios.publicaciones;

import java.util.List;

public class PublicacionMascota {
    Integer idPubli;
    String idMascota;
    float latitud;
    float longitud;
    List<String> listaFotos;
    String descripcion;
    String idHogaresDeTransito;
    String idRescatista;

    public PublicacionMascota(String idMascota, float latitud, float longitud, List<String> listaFotos, String descripcion) {
        this.idMascota = idMascota;
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
    }

    public void setIdPubli(Integer idPubli) {
        this.idPubli = idPubli;
    }
}
