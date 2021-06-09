package dds.domain.asociacion;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Imagen {

    public Imagen() {
    }

    public BufferedImage redimensionar(String archivo, Integer altoN, Integer anchoN ){ //ruta de archivo y el porcentaje que se desea reducir colta

        BufferedImage bf = null; //aca se va a cargar la imagen
        try {
            bf = ImageIO.read(new File(archivo));
        } catch (IOException ex) {
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex); //manejo de error de archivo
        }
        int ancho = bf.getWidth(); //ancho y alto de img original
        int alto = bf.getHeight(); //""
        int escalaAncho = (anchoN);
        int escalaAlto = (altoN);
        BufferedImage bufim = new BufferedImage(escalaAncho, escalaAlto, bf.getType());  //Se guarda la nueva imagen creada
        Graphics2D g = bufim.createGraphics(); //provee metodos para laburar con imagenes
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bf, 0,0, escalaAncho,escalaAlto, 0,0,ancho,alto, null); //redibuja la imagen
        g.dispose();
        return bufim;
    }



}