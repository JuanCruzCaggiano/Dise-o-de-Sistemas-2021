package dds.domain.persona.transaccion;

import dds.db.RepositorioHogaresDeTransito;
import dds.servicios.apiHogares.ServicioHogarDeTransito;
import dds.servicios.apiHogares.HogarDeTransito;

import java.util.ArrayList;
import java.util.List;

public class BuscarHogarDeTransito implements Transaccion {
    final  int idTransaccion = 2;
    private double lat;
    private double longitud;
    private double radio;
    List<HogarDeTransito> posiblesHogares = new ArrayList<>();
    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public BuscarHogarDeTransito(){
    }

    //CONSTRUCTOR PARA EJECUTAR TRANSACCION
    public BuscarHogarDeTransito(double lat, double longitud, double radio) {
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
