package dds.servicios.publicaciones;

import dds.domain.mascota.Sexo;

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

    @Enumerated(EnumType.STRING)
    private TipoPublicacion tipoPublicacion;

    @Column
    String idHogaresDeTransito;

    @Column
    String idRescatista;

/*
    @ManyToOne
    @JoinColumn(name = "FK_PUBLICADOR", updatable = false)
    private Publicador publicador;
    //PRUEBA
    public PublicacionMascota(Publicador publicador,String idMascota, double latitud, double longitud, List<String> listaFotos, String descripcion,String idRescatista) {
        this.idPublicacion = UUID.randomUUID().toString().replace("-", "");
        this.idMascota = idMascota;
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
        this.idRescatista = idRescatista;
        this.publicador = publicador;
    }
    public Publicador getPublicador() {
        return publicador;
    }

    public void setPublicador(Publicador publicador) {
        this.publicador = publicador;
    }*/

    public PublicacionMascota(String idMascota, double latitud, double longitud, List<String> listaFotos, String descripcion,String idRescatista,TipoPublicacion tipo) {
        this.idPublicacion = UUID.randomUUID().toString().replace("-", "");
        this.idMascota = idMascota;
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
        this.idRescatista = idRescatista;
        this.tipoPublicacion = tipo;
    }



    public PublicacionMascota(double latitud, double longitud, List<String> listaFotos, String descripcion,String idRescatista,TipoPublicacion tipo) {
        this.idPublicacion = UUID.randomUUID().toString().replace("-", "");
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaFotos = listaFotos;
        this.descripcion = descripcion;
        this.idRescatista= idRescatista;
        this.tipoPublicacion = tipo;
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

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }
}
