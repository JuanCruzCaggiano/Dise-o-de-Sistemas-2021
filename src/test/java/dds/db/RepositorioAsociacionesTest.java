package dds.db;

import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
import dds.servicios.apiHogares.Ubicacion;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RepositorioAsociacionesTest {

    Asociacion asoc;

    @Before
    public void setUp(){
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();
        asoc = new Asociacion("Asco",new Ubicacion("DIR",0,0));
        asoc.setIdAsociacion(1);
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);

    }

    @Test
    public void testGetAsociacion() {
        Assert.assertEquals(1,RepositorioAsociaciones.getRepositorio().getAsociacion(1).getIdAsociacion());
    }

    @Test (expected = LogicRepoException.class)
    public void testGetAsociacionError() {
        Assert.assertEquals(1,RepositorioAsociaciones.getRepositorio().getAsociacion(777).getIdAsociacion());
    }

    @Test
    public void testEliminaAsociacion() {
        RepositorioAsociaciones.getRepositorio().eliminarAsociacion(asoc);
        Assert.assertEquals(0,RepositorioAsociaciones.getRepositorio().getAsociaciones().size());
    }

}