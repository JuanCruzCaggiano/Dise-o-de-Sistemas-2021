package dds.domain.mascota;

import java.util.HashMap;
import java.util.List;

public class Mascota {
    private String idAsociacion;
    private String idMascota;
    private TipoMascota tipo;
    private String nombre;
    private String apodo;
    private Integer edad;
    private String descripcion;
    private List<String> listaFotos;
    //private List<ConfigCaracMascota> caracteristica;
    private HashMap <String, Object> caracteristica = new HashMap <String, Object> ();
    private Boolean estaPerdida = false;

    public Mascota(TipoMascota tipo, String nombre, String apodo, Integer edad, String descripcion, List<String> listaFotos, HashMap <String, Object> caracteristica) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apodo = apodo;
        this.edad = edad;
        this.descripcion = descripcion;
        this.listaFotos = listaFotos;
        this.caracteristica = caracteristica;

    }

    public HashMap<String, Object> getCaracteristica() {
        return caracteristica;
    }

    public void agregarCaracteristica(String key, String value){
        caracteristica.put(key,value);
    }  // POR FRONT NADA MAS SE VA A MOSTRAR LAS KEYS QUE AGREGO ASOCIACION PARA QUE EL DUEÑO PUEDA AGREGARLAS
    public void eliminarCaracteristica(String key){
        caracteristica.remove(key);
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public void setEstaPerdida(Boolean estaPerdida) {
        this.estaPerdida = estaPerdida;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public Mascota(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }


}
