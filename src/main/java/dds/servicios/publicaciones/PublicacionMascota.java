package dds.servicios.publicaciones;

import java.util.List;

public class PublicacionMascota {
    String idPublicacion;
    String idMascota;
    float latitud;
    float longitud;
    List<String> listaFotos;
    String descripcion;
    String idHogaresDeTransito;
    String idRescatista;

    public PublicacionMascota(String idMascota, float latitud, float longitud, List<String> listaFotos, String descripcion,String idRescatita) {
        this.idMascota = idMascota;
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
        this.idRescatista = idRescatista;
    }

    public PublicacionMascota(float latitud, float longitud, List<String> listaFotos, String descripcion,String idRescatista) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
        this.idRescatista= idRescatista;
    }

    public void setIdPublicacion(String idPubli) {
        this.idPublicacion = idPubli;
    }

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public String getIdRescatista() {
        return idRescatista;
    }

    public void setIdHogaresDeTransito(String idHogaresDeTransito) {
        this.idHogaresDeTransito = idHogaresDeTransito;
    }

    public void setIdRescatista(String idRescatista) {
        this.idRescatista = idRescatista;
    }
}
