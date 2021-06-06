package dds.servicios.apiHogares;

public class Ubicacion {
    private String direccion;
    private int lat;
    private int longitud;


    public Ubicacion(String direccion, int lat, int longitud) {
        this.direccion = direccion;
        this.lat = lat;
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}
