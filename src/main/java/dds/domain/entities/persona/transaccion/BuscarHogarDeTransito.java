package dds.domain.entities.persona.transaccion;

import dds.db.RepositorioHogaresDeTransito;
import dds.servicios.apiHogares.ServicioHogarDeTransito;
import dds.servicios.apiHogares.HogarDeTransito;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("buscar_hogar")
public class BuscarHogarDeTransito extends Transaccion {
    @Transient
    private double lat;
    @Transient
    private double longitud;
    @Transient
    private double radio;
    @Transient
    List<HogarDeTransito> posiblesHogares = new ArrayList<>();
    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public BuscarHogarDeTransito(){
        this.idTransaccion = 2;
    }

    //CONSTRUCTOR PARA EJECUTAR TRANSACCION
    public BuscarHogarDeTransito(double lat, double longitud, double radio) {
        this.idTransaccion = 2;
        this.lat = lat;
        this.longitud = longitud;
        this.radio = radio;
    }

    public List<HogarDeTransito> getPosiblesHogares() {
        return posiblesHogares;
    }

    //BUSCAR HOGAR DE TRANSITO
    @Override
    public void ejecutar() {
        ServicioHogarDeTransito.getInstance().actualizarRepositorioHogaresDeTransito();
        posiblesHogares.clear();
        this.posiblesHogares.addAll(RepositorioHogaresDeTransito.getRepositorio().filtrarPorDistancia(lat,longitud,radio));
    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }


}
