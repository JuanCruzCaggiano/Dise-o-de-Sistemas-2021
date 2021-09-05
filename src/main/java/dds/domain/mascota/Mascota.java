package dds.domain.mascota;

import dds.domain.asociacion.Asociacion;
import dds.domain.persona.Persona;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


@Entity
@Table (name = "mascota")
public class Mascota {

    @Id
    @Column (name = "id")
    private String idMascota;

    @Enumerated(EnumType.STRING)
    private TipoMascota tipo;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column
    private String nombre;

    @Column
    private String apodo;

    @Column (columnDefinition = "DATE")
    private Date fechaNac;

    @Column
    private String descripcion;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "listaFotos")
    private List<String> listaFotos = new ArrayList<>();

    //private List<ConfigCaracMascota> caracteristica;

    @Transient //TODO
    private HashMap <String, Object> caracteristica = new HashMap <String, Object> ();

    @Column
    private Boolean estaPerdida = false;


    public Mascota(TipoMascota tipo, String nombre, String apodo, LocalDate fechaNac, String descripcion, List<String> listaFotos, HashMap <String, Object> caracteristica,Sexo sexo) {
        this.idMascota= UUID.randomUUID().toString().replace("-", ""); //TODO HELPER GENERADOR
        this.tipo = tipo;
        this.nombre = nombre;
        this.apodo = apodo;
        this.sexo = sexo;
        this.fechaNac = Date.from(fechaNac.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
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
