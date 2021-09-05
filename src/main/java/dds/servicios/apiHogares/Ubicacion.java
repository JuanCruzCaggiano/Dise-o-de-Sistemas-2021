package dds.servicios.apiHogares;

import com.google.gson.annotations.SerializedName;

public class Ubicacion {
    private String direccion;
    private double lat;
    @SerializedName("long")
    private double longitud;

    public Ubicacion(String direccion, double lat, double longitud) {
        this.direccion = direccion;
        this.lat = lat;
        this.longitud = longitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
