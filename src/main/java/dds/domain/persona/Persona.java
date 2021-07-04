package dds.domain.persona;

import dds.domain.mascota.Mascota;
import dds.domain.mascota.mascotaException.LogicMascotaException;
import dds.domain.persona.roles.RolPersona;
import dds.domain.persona.transaccion.Transaccion;
import dds.servicios.avisos.AdapterFormaNotificacion;
import dds.servicios.avisos.Notificador;


import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String idPersona;
    private List<Mascota> mascotas;
    private LocalDate fechaNac;
    private TipoDocumento tipoDoc;
    private Integer nroDoc;
    private String direccion;
    private List<RolPersona> listaRoles;
    private Notificador notificador;

    public Persona(String nombre, String apellido, List<Mascota> mascotas, List<RolPersona> listaRoles, Notificador notificador) {
        this.mascotas = mascotas;
        this.listaRoles = listaRoles;
        this.notificador = notificador;
        this.listaRoles = new ArrayList<>();
        notificador.getSuscriptores().get(0).setNombre(nombre);
        notificador.getSuscriptores().get(0).setApellido(apellido);
    }
    //Alta de persona que encontro a su mascota
    public Persona(String nombre, String apellido,TipoDocumento tipoDoc,Integer nroDoc,LocalDate fechaNac,String direccion,String telefono, String email,List<AdapterFormaNotificacion> formasDeNoti) {
        this.tipoDoc = tipoDoc;
        this.nroDoc = nroDoc;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();
        this.listaRoles = new ArrayList<>();
        this.notificador = new Notificador();
        notificador.agendarContacto(nombre,apellido,telefono,email,formasDeNoti);

    }

    public String getNombre() {
        return notificador.getSuscriptores().get(0).getNombre();
    }
    public String getApellido() {
        return notificador.getSuscriptores().get(0).getApellido();
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

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public TipoDocumento getTipoDoc() {
        return tipoDoc;
    }

    public Integer getNroDoc() {
        return nroDoc;
    }

    public String getDireccion() {
        return direccion;
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

    public List<RolPersona> getListaRoles() {
        return listaRoles;
    }
    public void ejecutarTransaccion(Transaccion transaccion)  {
        for(RolPersona rol: listaRoles){
            rol.ejecutarTransaccion(transaccion);
        }
    }

    public String getEmail() {
        return this.notificador.getSuscriptores().get(0).getEmail();
    }
    public String getTelefono() {
        return this.notificador.getSuscriptores().get(0).getTelefono();
    }
    public void agregarRol(RolPersona rol){
        this.listaRoles.add(rol);
    }
}
