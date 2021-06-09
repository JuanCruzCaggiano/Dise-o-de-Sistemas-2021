package dds.domain.seguridad.usuario;

import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioUsuarios;
import dds.domain.asociacion.Asociacion;
import junit.framework.TestCase;
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
        asoc = new Asociacion("Asco","AsocDir","AsocLoc","AscoProv","AscoPais","AsocCod");
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

    @Test
    public void testModificarTamanioFotos() {
        admin.modificarTamanioFotos(1100,850);
        asoc.getConfiguraciones().cambiarTamanio("imgprueba.jpg","recorte2.jpg");
    }

}