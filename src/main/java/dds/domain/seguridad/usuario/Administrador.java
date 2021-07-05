package dds.domain.seguridad.usuario;

import java.security.NoSuchAlgorithmException;

public class Administrador extends Usuario {


    public Administrador(String userName,String passWord) throws NoSuchAlgorithmException {
        super(userName,passWord);
    }

    public void agregarCaracteristica(String c){
        this.getAsociacion().getConfiguraciones().agregarCaracteristicaMascota(c);
    }

    public void eliminarCaracteristica(String c){
        this.getAsociacion().getConfiguraciones().eliminarCaracteristicas(c);
    }

    public  void modificarTamanioFotos(Integer ancho, Integer alto){
        this.getAsociacion().getConfiguraciones().setAltoFoto(alto);
        this.getAsociacion().getConfiguraciones().setAnchoFoto(ancho);
    }
    public void agregarPreguntar(String preg){
        this.getAsociacion().getConfiguraciones().agregarPreguntaNueva(preg);
    }
    public void eliminarPregunta(String preg){
        this.getAsociacion().getConfiguraciones().eliminarPregunta(preg);
    }

}
