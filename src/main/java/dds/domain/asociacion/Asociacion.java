package dds.domain.asociacion;

import dds.db.RepositorioAsociaciones;
import dds.servicios.publicaciones.Publicador;

import java.util.ArrayList;
import java.util.List;

public class Asociacion {

    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;
    private String pais;
    private String codPostal;
    private List<Configuracion> configuraciones = new ArrayList<>();
    private Publicador publicador;

    public Asociacion(String nombre, String direccion, String localidad, String provincia, String pais, String codPostal) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.codPostal = codPostal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getNombre() {
        return nombre;
    }





    public void agregarAsociacion(String nombre, String direccion, String localidad, String provincia, String pais, String codPostal, RepositorioAsociaciones repo){
        Asociacion asoc = new Asociacion(nombre,direccion,localidad,provincia,pais,codPostal);

        repo.agregarAsociacion(asoc);
    }

    public void quitarAsociacion(Asociacion asociacionAElminar, RepositorioAsociaciones repo){
        repo.remover(asociacionAElminar);
    }

}
