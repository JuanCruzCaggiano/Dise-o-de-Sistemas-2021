package dds.servicios.avisos;

import java.util.List;

public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private List<AdapterFormaNotificacion> formasNotificacion;



    public Contacto(String nombre, String apellido, String telefono, String email, List<AdapterFormaNotificacion> adaptadores) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono; //SOLO INGRESAR 11 xxxx xxxx
        this.email = email;
        this.formasNotificacion = adaptadores;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }

    public String getApellido() {
        return apellido;
    }

    public List<AdapterFormaNotificacion> getFormasNotificacion() {
        return formasNotificacion;
    }
}
