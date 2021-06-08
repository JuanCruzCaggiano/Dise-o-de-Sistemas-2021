package dds.db;

import dds.domain.mascota.TipoMascota;
import dds.servicios.apiHogares.HogarDeTransito;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepoHogaresDeTransito {

    static List<HogarDeTransito> hogares = new ArrayList<>();

    private static RepoHogaresDeTransito repoHogaresDeTransito = new RepoHogaresDeTransito() ;

    public static RepoHogaresDeTransito getInstance(){
        return repoHogaresDeTransito;
    }

    public List<HogarDeTransito> getRepositorio() {
         return hogares;
    }

    public void setRepositorio(List<HogarDeTransito> hogares){
        this.hogares = hogares;
    }

    //filtrar aceptar perros/gatos o ambos
    public List<HogarDeTransito> filtrarPorTipoDeAnimal(TipoMascota tipoMascota){
        Boolean filtro = true;
        if (tipoMascota == TipoMascota.GATO) {
            return filtrarPorGato(filtro);
        }
        else {
            return filtrarPorPerro(filtro);
        }
    }

    public List<HogarDeTransito> filtrarPorGato(Boolean filtro){
        return this.hogares.stream().filter(p -> p.getAdmision().getGato().equals(filtro)).collect(Collectors.toList());
    }

    public List<HogarDeTransito> filtrarPorPerro(Boolean filtro){
        return this.hogares.stream().filter(p -> p.getAdmision().getPerro().equals(filtro)).collect(Collectors.toList());
    }

    //filtrar por ambos.
    public List<HogarDeTransito> filtrarPorAmbosTipoDeAnimal(){
        Boolean filtro = true;
        return this.hogares.stream().filter(p -> (p.getAdmision().getGato().equals(filtro))|| (p.getAdmision().getPerro().equals(filtro))).collect(Collectors.toList());
    }

    //filtrar por patio
    public List<HogarDeTransito> filtrarPorPatio(){
        Boolean filtro = true;
        return this.hogares.stream().filter(p -> p.getPatio().equals(filtro)).collect(Collectors.toList());
    }

    //filtrar por lugares disponibles
    public List<HogarDeTransito> filtrarPorDisponibilidad(){
        return this.hogares.stream().filter(p -> p.getLugares_disponibles() > 0).collect(Collectors.toList());

    }

    //filtrar por lugares disponibles
    public List<HogarDeTransito> filtrarPorCaracteristica(List<String> caracteristicas){
        if (caracteristicas.isEmpty()){
            return this.hogares;

        } else{
            return this.hogares.stream().filter(p -> p.getCaracteristicas().containsAll(caracteristicas)).collect(Collectors.toList());
        }
    }
    //filtrar por rango de distancia entre Rescatista y hogares
    public List<HogarDeTransito> filtrarPorDisponibilidad(float latitudComparar, float longitudComparar, double radiocercania){
        return this.hogares.stream().filter(p -> distanciaCoord(p.getUbicacion().getLat(),p.getUbicacion().getLng1(),latitudComparar,longitudComparar) <= radiocercania).collect(Collectors.toList());
    }



    //calculo de distancia
    public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
        //double radioTierra = 6371000;//probar en metros tambien
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia;
    }

}



