package dds.domain.asociacion;



import dds.db.RepositorioAsociaciones;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.publicaciones.Publicador;

import java.util.ArrayList;
import java.util.List;

public class Asociacion {

    private String idAsociacion;
    private String nombre;
    private Ubicacion ubicacion;
    private Publicador publicador;
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

    public void setIdAsociacion(String idAsociacion) {
        this.idAsociacion = idAsociacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdAsociacion() {
        return idAsociacion;
    }

    public Publicador getPublicador() {
        return publicador;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
}
