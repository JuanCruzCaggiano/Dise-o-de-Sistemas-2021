package dds.domain.asociacion;
import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioHogaresDeTransito;
import dds.db.RepositorioPersonas;
import dds.domain.mascota.Mascota;
import dds.db.RepositorioUsuarios;
import dds.domain.mascota.Sexo;
import dds.domain.mascota.TipoMascota;
import dds.servicios.apiHogares.Ubicacion;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfiguracionesTest extends TestCase {

    String fotoAModif = "imgprueba.jpg";
    String fotoModif = "recorte.jpg";
    Asociacion asoc;
    Mascota mascota;
    @Before
    public void setUp() throws NoSuchAlgorithmException {
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();
        //CREO ASOC
        asoc = new Asociacion("Asco",new Ubicacion("DIR",0,0));
        asoc.setIdAsociacion(1);
        asoc.getConfiguraciones().agregarCaracteristicaMascota("Color de Pelo");
        asoc.getConfiguraciones().agregarCaracteristicaMascota("Tamaño");
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);
        mascota = new Mascota(TipoMascota.PERRO,"lola beatriz","lola", LocalDate.now().minusYears(6),"bruta beba",new ArrayList<>(),new HashMap<>(), Sexo.MACHO);


    }


    @Test
    public void testFoto() {
        asoc.getConfiguraciones().cambiarTamanio(fotoAModif,fotoModif);
    }
    @Test
    public void testAgregarCaracteristica() {
        Assert.assertEquals(2,RepositorioAsociaciones.getRepositorio().getAsociacion(1).getConfiguraciones().getKeys().size());
    }
    @Test
    public void testEliminarCaracteristica() {
        RepositorioAsociaciones.getRepositorio().getAsociacion(1).getConfiguraciones().eliminarCaracteristicas("Color de Pelo");
        Assert.assertEquals(1,RepositorioAsociaciones.getRepositorio().getAsociacion(1).getConfiguraciones().getKeys().size());
    }
    @Test
    public void testAgregarCaracteristicaAMascota() {
        mascota.agregarCaracteristica("Color De Pelo","Negro y Marron");
        mascota.agregarCaracteristica("Tamaño","Grande");
        //System.out.println (mascota.getCaracteristica().keySet ()); //trae solo keys y el de abajo solo values
        //System.out.println (mascota.getCaracteristica().values ());
        System.out.println (mascota.getCaracteristica());
    }
    @Test
    public void testEliminarCaracteristicaAMascota() {
        mascota.agregarCaracteristica("Color De Pelo","Negro y Marron");
        mascota.agregarCaracteristica("Tamaño","Grande");
        System.out.println (mascota.getCaracteristica());
        mascota.eliminarCaracteristica("Color De Pelo");
        System.out.println ("Se borra caracteristica Color De Pelo");
        System.out.println (mascota.getCaracteristica());
    }
}