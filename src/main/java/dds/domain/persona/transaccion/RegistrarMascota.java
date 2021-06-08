package dds.domain.persona.transaccion;

import dds.domain.asociacion.ConfigCaracMascota;
import dds.domain.mascota.Mascota;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class RegistrarMascota implements Transaccion {
    final  int idTransaccion = 7;
    private Persona dueño;
    private TipoMascota tipo;
    private String nombre;
    private String apodo;
    private Integer edad;
    private String descripcion;
    private List<String> listaFotos;
    private List<ConfigCaracMascota> caracteristica;

    public RegistrarMascota() {
    }

    public RegistrarMascota(Persona dueño, TipoMascota tipo, String nombre, String apodo, Integer edad, String descripcion, List<String> listaFotos, List<ConfigCaracMascota> caracteristica) {
        this.dueño = dueño;
        this.tipo = tipo;
        this.nombre = nombre;
        this.apodo = apodo;
        this.edad = edad;
        this.descripcion = descripcion;
        this.listaFotos = listaFotos;
        this.caracteristica = caracteristica;
    }

    //REGISTRAR MASCOTA
    @Override
    public void ejecutar(){
        Mascota nueva = new Mascota(tipo,nombre,apodo,edad,descripcion,listaFotos,caracteristica);
        dueño.getMascotas().add(nueva);
    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }


}
