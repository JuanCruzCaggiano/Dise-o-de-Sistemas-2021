package dds.domain.asociacion;



import dds.db.RepositorioAsociaciones;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.publicaciones.Publicador;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Entity
@Table (name = "asociacion")
public class Asociacion {

    @Id
    @GeneratedValue
    private int idAsociacion;

    @Column
    private String nombre;

    @Transient
    private Ubicacion ubicacion;

    @Transient //TODO
    private Publicador publicador;

    @Transient //TODO
    private Configuraciones configuraciones;

    public Asociacion(String nombre, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.publicador = new Publicador();
        this.configuraciones = new Configuraciones();
    }

    public Configuraciones getConfiguraciones() {
        return configuraciones;
    }

    public void setIdAsociacion(int idAsociacion) {
        this.idAsociacion = idAsociacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdAsociacion() {
        return idAsociacion;
    }

    public Publicador getPublicador() {
        return publicador;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

}
