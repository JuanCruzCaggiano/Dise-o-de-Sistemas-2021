package dds.servicios.publicaciones;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "publicacion_mascota")
public class PublicacionMascota {
    @Id
    String idPublicacion;

    @Column
    String idMascota;

    @Column
    double latitud;

    @Column
    double longitud;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "listaFotos")
    List<String> listaFotos = new ArrayList<>();

    @Column
    String descripcion;

    @Column
    String idHogaresDeTransito;

    @Column
    String idRescatista;

    public PublicacionMascota(String idMascota, double latitud, double longitud, List<String> listaFotos, String descripcion,String idRescatista) {
        this.idPublicacion = UUID.randomUUID().toString().replace("-", "");
        this.idMascota = idMascota;
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
        this.idRescatista = idRescatista;
    }

    public PublicacionMascota(double latitud, double longitud, List<String> listaFotos, String descripcion,String idRescatista) {
        this.idPublicacion = UUID.randomUUID().toString().replace("-", "");
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
