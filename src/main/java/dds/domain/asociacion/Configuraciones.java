package dds.domain.asociacion;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Configuraciones {
    private List<String> preguntasObligatorias= new ArrayList<>();
    private List<String> preguntasOpcionales;
    private Integer anchoFoto;
    private Integer altoFoto;
    private List<String> keys;



    public Configuraciones() {
        this.anchoFoto=720;
        this.altoFoto=480;
        this.keys = new ArrayList<>(); //se usa para el hashmap de mascotas
        this.preguntasOpcionales = new ArrayList<>();
        preguntasObligatorias.add("Tamaño");
        preguntasObligatorias.add("Temperamento");
        preguntasObligatorias.add("Esta Vacunado?");
    }

    public void setAnchoFoto(Integer anchoFoto) {
        this.anchoFoto = anchoFoto;
    }

    public void setAltoFoto(Integer altoFoto) {
        this.altoFoto = altoFoto;
    }

    public void cambiarTamanio(String archivoFoto, String rutaGuardado) {
        Imagen foto = new Imagen();
        try {
            ImageIO.write(foto.redimensionar(archivoFoto, anchoFoto, altoFoto),"jpg",new File(rutaGuardado));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    ///PREGUNTAS
    public void agregarPreguntaNueva(String preg){
        this.preguntasOpcionales.add(preg);
    }
    public void eliminarPregunta(String preg){
        this.preguntasOpcionales.remove(preg);
    }
    public List<String> getPreguntas() {

        List<String> listaTot;

        (listaTot= new ArrayList<String>(preguntasObligatorias)).addAll(preguntasOpcionales);

        return listaTot;
    }



}