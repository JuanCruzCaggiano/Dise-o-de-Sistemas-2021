package dds.domain.seguridad.usuario;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import dds.servicios.apiHogares.Ubicacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class AdministradorTest {

    Administrador admin;
    Asociacion asoc;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        RepositorioUsuarios.getRepositorio().getUsuarios().clear();
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();


        //CREO ADMIN
        admin = new Administrador("admin","Password123+");

        //CREO ASOC
        asoc = new Asociacion("Asco",new Ubicacion("DIR",0,0));
        asoc.setIdAsociacion("ASOC1");
        admin.setAsociacion(asoc);
        RepositorioUsuarios.getRepositorio().agregarUsuario(admin);
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);





    }


    @Test
    public void testAgregarCaracteristica() {
        admin.agregarCaracteristica("Color de ojos");
        Assert.assertEquals("Color de ojos",RepositorioAsociaciones.getRepositorio().getAsociacion(admin.getAsociacion().getIdAsociacion()).getConfiguraciones().getKeys().get(0));
    }

    @Test
    public void testEliminarCaracteristica() {
        admin.agregarCaracteristica("Color de ojos");
        admin.agregarCaracteristica("Tamanio");
        Assert.assertEquals(2,RepositorioAsociaciones.getRepositorio().getAsociacion(admin.getAsociacion().getIdAsociacion()).getConfiguraciones().getKeys().size());
        admin.eliminarCaracteristica("Tamanio");
        Assert.assertEquals(1,RepositorioAsociaciones.getRepositorio().getAsociacion(admin.getAsociacion().getIdAsociacion()).getConfiguraciones().getKeys().size());
        Assert.assertEquals("Color de ojos",RepositorioAsociaciones.getRepositorio().getAsociacion(admin.getAsociacion().getIdAsociacion()).getConfiguraciones().getKeys().get(0));
    }

    // Para los siguientes tests se usó una foto de 1600x900 píxeles [rda = 1.7777]
    @Test
    public void testModificarTamanioFotosA_192000x480() {
        admin.modificarTamanioFotos(192000, 480);
        asoc.getConfiguraciones().cambiarTamanio("imgprueba.jpg","recorte2.jpg");
        // No se obtiene una foto de 192000x480 [rda = 400.0000]...
        // ... sino una de 853x480 [rda = 1.7770], manteniendo la relación de aspecto original [1.7777].
    }
    @Test
    public void testModificarTamanioFotosA_64x480() {
        admin.modificarTamanioFotos(64, 480);
        asoc.getConfiguraciones().cambiarTamanio("imgprueba.jpg","recorte2.jpg");
        // No se obtiene una foto de 64x480 [rda = 0.1333]...
        // ... sino una de 64x36 [rda = 1.7777], manteniendo la relación de aspecto original [1.7777].
    }
    @Test
    public void testModificarTamanioFotosA_480x192000() { //
        admin.modificarTamanioFotos(480, 192000);
        asoc.getConfiguraciones().cambiarTamanio("imgprueba.jpg","recorte2.jpg");
        // No se obtiene una foto de 480x192000 [rda = 0.0025]...
        // ... sino una de 480x270 [rda = 1.7777], manteniendo la relación de aspecto original [1.7777].
    }
    @Test
    public void testModificarTamanioFotosA_480x64() { //
        admin.modificarTamanioFotos(480, 64);
        asoc.getConfiguraciones().cambiarTamanio("imgprueba.jpg","recorte2.jpg");
        // No se obtiene una foto de 480x64 [rda = 7.5]...
        // ... sino una de 113x64 [rda = 1.7656], manteniendo la relación de aspecto original [1.7777].
    }
    @Test
    public void testModificarTamanioFotosA_1280x1280() { //
        admin.modificarTamanioFotos(1280, 1280);
        asoc.getConfiguraciones().cambiarTamanio("imgprueba.jpg","recorte2.jpg");
        // No se obtiene una foto de 1280x1280 [rda = 1]...
        // ... sino una de 1280x720 [rda = 1.7777], manteniendo la relación de aspecto original [1.7777].
    }

}