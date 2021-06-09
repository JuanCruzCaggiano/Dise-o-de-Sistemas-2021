package dds.domain.asociacion;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuraciones {
    private Integer anchoFoto;
    private Integer altoFoto;
    private List<String> keys;



    public Configuraciones() {
        this.anchoFoto=720;
        this.altoFoto=480;
        this.keys = new ArrayList<>(); //se usa para el hashmap de mascotas
    }

    public void setAnchoFoto(Integer anchoFoto) {
        this.anchoFoto = anchoFoto;
    }

    public void setAltoFoto(Integer altoFoto) {
        this.altoFoto = altoFoto;
    }

    public void cambiarTamanio(String archivoFoto, String rutaGuardado) throws  IOException{
        Imagen foto = new Imagen();
        ImageIO.write(foto.redimensionar(archivoFoto,altoFoto,anchoFoto),"jpg",new File(rutaGuardado));
    }

    ////MASCOTAS

    public void agregarCaracteristicaMascota(String caracteristicaNueva){
        this.keys.add(caracteristicaNueva);
    }
    public void eliminarCaracteristicas(String caracABorrar){
        this.keys.remove(caracABorrar);
    }
    public List<String> getKeys() {
        return keys;
    }
    /*public void ejecutarCambioDeCaracteristicas(){
        modifica para todos?
    }*/


}