package dds.domain.entities.seguridad.usuario;

import dds.domain.entities.asociacion.Asociacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.security.NoSuchAlgorithmException;

@Entity
@DiscriminatorValue("A")
public class Administrador extends Usuario {

    public Administrador() {
    }
    @Override
    public Boolean soyAdmin(){
        return true;
    }
    public Administrador(String userName, String passWord, Asociacion asoc) throws NoSuchAlgorithmException {
        super(userName,passWord,asoc);
    }

    public void agregarCaracteristica(String c){
        this.getAsociacion().getConfigurador().agregarCaracteristicaMascota(c);
    }

    public void eliminarCaracteristica(String c){
        this.getAsociacion().getConfigurador().eliminarCaracteristicas(c);
    }

    public  void modificarTamanioFotos(Integer ancho, Integer alto){
        this.getAsociacion().getConfigurador().setAltoFoto(alto);
        this.getAsociacion().getConfigurador().setAnchoFoto(ancho);

    }
    public void agregarPregunta(String preg){
        this.getAsociacion().getConfigurador().agregarPreguntaNueva(preg);
    }
    public void eliminarPregunta(String preg){
        this.getAsociacion().getConfigurador().eliminarPregunta(preg);
    }



}
