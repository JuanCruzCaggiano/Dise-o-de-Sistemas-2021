package dds.domain.mascota;

import dds.domain.asociacion.ConfigCaracMascota;

import java.util.List;

public class Mascota {
    private String idMascota:
    private TipoMascota tipo;
    private String nombre;
    private String apodo;
    private Integer edad;
    private String descripcion;
    private List<String> listaFotos;
    private List<ConfigCaracMascota> caracteristica;
    private Boolean estaPerdida;

    public Mascota(TipoMascota tipo, String nombre, String apodo, Integer edad, String descripcion, List<String> listaFotos, List<ConfigCaracMascota> caracteristica, Boolean estaPerdida) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apodo = apodo;
        this.edad = edad;
        this.descripcion = descripcion;
        this.listaFotos = listaFotos;
        this.caracteristica = caracteristica;
        this.estaPerdida = estaPerdida;
    }

    public void setEstaPerdida(Boolean estaPerdida) {
        this.estaPerdida = estaPerdida;
    }

    public Mascota(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
