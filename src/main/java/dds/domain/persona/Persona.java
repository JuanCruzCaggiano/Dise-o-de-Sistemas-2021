package dds.domain.persona;

import dds.domain.mascota.Mascota;
import dds.domain.mascota.mascotaException.LogicMascotaException;
import dds.servicios.avisos.Notificador;


import java.time.LocalDate;
import java.util.List;

public class Persona {
    private String idPersona;
    private String nombre;
    private String apellido;
    private List<Mascota> mascotas;
    private LocalDate fechaNac;
    private TipoDocumento tipoDoc;
    private Integer nroDoc;
    private List<RolPersona> listaRoles;
    private Notificador notificador;

    public Persona(String nombre, String apellido, List<Mascota> mascotas, List<RolPersona> listaRoles, Notificador notificador) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mascotas = mascotas;
        this.listaRoles = listaRoles;
        this.notificador = notificador;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public Notificador getNotificador() {
        return notificador;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public Mascota getMascota(String idMascota){
        Mascota m = mascotas.stream().filter(mascota -> mascota.getIdMascota().equals(idMascota)).findFirst().orElse(null);
        if(m ==null){
            throw new LogicMascotaException("El dueño no posee este id_mascota");
        }
        return m;
    }
}
