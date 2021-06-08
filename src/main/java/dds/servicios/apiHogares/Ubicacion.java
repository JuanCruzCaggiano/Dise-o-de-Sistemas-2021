package dds.servicios.apiHogares;

import com.google.gson.annotations.SerializedName;

public class Ubicacion {
    private String direccion;
    private float lat;
    @SerializedName("long")
    private float longitud;

    public Ubicacion(String direccion, float lat, float longitud) {
        this.direccion = direccion;
        this.lat = lat;
        this.longitud = longitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getLat() {
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
