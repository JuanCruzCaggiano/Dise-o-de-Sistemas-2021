package dds.servicios.apiHogares;


public class HogarDeTransito {
    private String total;
    private String offset;
    private Ubicacion ubicacion;
    private String telefono;
    private Admision admision;
    private String id;
    private String nombre;
    private int capacidad;
    private int lugares_disponibles;
    private Boolean patio;
    private String caracteristicas;


    public HogarDeTransito(String total, String offset, Ubicacion ubicacion, String telefono, Admision admision, String id, String nombre, int capacidad, int lugares_disponibles, Boolean patio, String caracteristicas) {
        this.total = total;
        this.offset = offset;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.admision = admision;
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.lugares_disponibles = lugares_disponibles;
        this.patio = patio;
        this.caracteristicas = caracteristicas;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Admision getAdmision() {
        return admision;
    }

    public void setAdmision(Admision admision) {
        this.admision = admision;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getLugares_disponibles() {
        return lugares_disponibles;
    }

    public void setLugares_disponibles(int lugares_disponibles) {
        this.lugares_disponibles = lugares_disponibles;
    }

    public Boolean getPatio() {
        return patio;
    }

    public void setPatio(Boolean patio) {
        this.patio = patio;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
