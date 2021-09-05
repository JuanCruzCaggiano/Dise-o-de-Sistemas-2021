package dds.domain.persona;

import dds.domain.mascota.Mascota;
import dds.domain.mascota.mascotaException.LogicMascotaException;
import dds.domain.persona.roles.Duenio;
import dds.domain.persona.roles.RolPersona;
import dds.domain.persona.transaccion.Transaccion;
import dds.servicios.avisos.AdapterFormaNotificacion;
import dds.servicios.avisos.Notificador;
import dds.servicios.helpers.DateHelper;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @Column
    private String idPersona;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Mascota> mascotas = new ArrayList<>();

    @Column (columnDefinition = "DATE")
    private Date fechaNac;

    @Column
    private TipoDocumento tipoDoc;

    @Column
    private Integer nroDoc;

    @Column
    private String direccion;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<RolPersona> listaRoles = new ArrayList<>();

    @OneToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "notificador_id")
    private Notificador notificador;

    public Persona(String nombre, String apellido, List<Mascota> mascotas, List<RolPersona> listaRoles, Notificador notificador) {
        this.idPersona = UUID.randomUUID().toString().replace("-", "");
        this.mascotas = mascotas;
        this.listaRoles = listaRoles;
        this.notificador = notificador;
        notificador.getContactos().get(0).setNombre(nombre);
        notificador.getContactos().get(0).setApellido(apellido);
    }
    //Alta de persona que encontro a su mascota
    public Persona(String nombre, String apellido,TipoDocumento tipoDoc,Integer nroDoc,LocalDate fechaNac,String direccion,String telefono, String email,List<AdapterFormaNotificacion> formasDeNoti) {
        this.idPersona = UUID.randomUUID().toString().replace("-", "");
        this.tipoDoc = tipoDoc;
        this.nroDoc = nroDoc;
        this.agregarRol(Duenio.getDuenio());
        this.fechaNac = DateHelper.getHelper().LocalDateToDate(fechaNac);
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();
        this.listaRoles = new ArrayList<>();
        this.notificador = new Notificador();
        notificador.agendarContacto(nombre,apellido,telefono,email,formasDeNoti);

    }

    public String getNombre() {
        return notificador.getContactos().get(0).getNombre();
    }
    public String getApellido() {
        return notificador.getContactos().get(0).getApellido();
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

    public Date getFechaNac() {
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
        return this.notificador.getContactos().get(0).getEmail();
    }
    public String getTelefono() {
        return this.notificador.getContactos().get(0).getTelefono();
    }
    public void agregarRol(RolPersona rol){
        this.listaRoles.add(rol);
    }
}
