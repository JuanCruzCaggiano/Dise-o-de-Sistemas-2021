package dds.domain.persona.transaccion;

import dds.domain.mascota.Mascota;
import dds.domain.mascota.Sexo;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Entity
@DiscriminatorValue("registrar_mascota")
public class RegistrarMascota extends Transaccion {
    @Transient
    private Persona dueño;
    @Transient
    private TipoMascota tipo;
    @Transient
    private String nombre;
    @Transient
    private String apodo;
    @Transient
    private LocalDate fechaNac;
    @Transient
    private String descripcion;
    @Transient
    private Sexo sexo;
    @Transient
    private List<String> listaFotos;
    @Transient
    private HashMap <String, Object> caracteristica = new HashMap <String, Object> ();

    public RegistrarMascota() {
//        this.idTransaccion = 7;
    }

    public RegistrarMascota(Persona dueño, TipoMascota tipo, String nombre, String apodo, LocalDate fnac, String descripcion, List<String> listaFotos, HashMap<String, Object> caracteristica, Sexo sexo) {
//        this.idTransaccion = 7;
        this.dueño = dueño;
        this.tipo = tipo;
        this.nombre = nombre;
        this.apodo = apodo;
        this.fechaNac = fnac;
        this.descripcion = descripcion;
        this.listaFotos = listaFotos;
        this.caracteristica = caracteristica;
        this.sexo = sexo;
    }

    //REGISTRAR MASCOTA
    @Override
    public void ejecutar(){
        Mascota nueva = new Mascota(tipo,nombre,apodo,fechaNac,descripcion,listaFotos,caracteristica,sexo);
        dueño.getMascotas().add(nueva);
    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }


}
