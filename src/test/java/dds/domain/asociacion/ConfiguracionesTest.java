package dds.domain.asociacion;
import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioHogaresDeTransito;
import dds.db.RepositorioPersonas;
import dds.domain.mascota.Mascota;
import dds.db.RepositorioUsuarios;
import dds.domain.mascota.TipoMascota;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfiguracionesTest extends TestCase {

    String fotoAModif = "imgprueba.jpeg";
    String fotoModif = "recorte.jpg";
    Configuraciones configFoto= new Configuraciones();
    Asociacion asoc;
    Mascota mascota;
    @Before
    public void setUp() throws NoSuchAlgorithmException {
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();
        //CREO ASOC
        asoc = new Asociacion("Asco", "AsocDir", "AsocLoc", "AscoProv", "AscoPais", "AsocCod");
        asoc.setIdAsociacion("ASOC1");
        asoc.getConfiguraciones().agregarCaracteristicaMascota("Color de Pelo");
        asoc.getConfiguraciones().agregarCaracteristicaMascota("Tamaño");
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);
        mascota = new Mascota(TipoMascota.PERRO,"lola beatriz","lola",6,"bruta beba",new ArrayList<>(),new HashMap<>());


    }


    @Test
    public void testFoto() throws IOException {
        configFoto.cambiarTamanio(fotoAModif,fotoModif);
    }
    @Test
    public void testAgregarCaracteristica() throws IOException {
        Assert.assertEquals(2,RepositorioAsociaciones.getRepositorio().getAsociacion("ASOC1").getConfiguraciones().getKeys().size());
    }
    @Test
    public void testEliminarCaracteristica() throws IOException {
        RepositorioAsociaciones.getRepositorio().getAsociacion("ASOC1").getConfiguraciones().eliminarCaracteristicas("Color de Pelo");
        Assert.assertEquals(1,RepositorioAsociaciones.getRepositorio().getAsociacion("ASOC1").getConfiguraciones().getKeys().size());
    }
    @Test
    public void testAgregarCaracteristicaAMascota() throws IOException {
        mascota.agregarCaracteristica("Color De Pelo","Negro y Marron");
        mascota.agregarCaracteristica("Tamaño","Grande");
        //System.out.println (mascota.getCaracteristica().keySet ()); //trae solo keys y el de abajo solo values
        //System.out.println (mascota.getCaracteristica().values ());
        System.out.println (mascota.getCaracteristica());
    }
    @Test
    public void testEliminarCaracteristicaAMascota() throws IOException {
        mascota.agregarCaracteristica("Color De Pelo","Negro y Marron");
        mascota.agregarCaracteristica("Tamaño","Grande");
        System.out.println (mascota.getCaracteristica());
        mascota.eliminarCaracteristica("Color De Pelo");
        System.out.println ("Se borra caracteristica Color De Pelo");
        System.out.println (mascota.getCaracteristica());
    }
}