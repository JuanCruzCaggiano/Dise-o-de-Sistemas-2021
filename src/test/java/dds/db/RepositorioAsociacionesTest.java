package dds.db;

import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RepositorioAsociacionesTest {

    Asociacion asoc;

    @Before
    public void setUp(){
        RepositorioAsociaciones.getRepositorio().getAsociaciones().clear();
        asoc = new Asociacion("Asco","AsocDir","AsocLoc","AscoProv","AscoPais","AsocCod");
        asoc.setIdAsociacion("ASOC1");
        RepositorioAsociaciones.getRepositorio().agregarAsociacion(asoc);

    }

    @Test
    public void testGetAsociacion() {
        Assert.assertEquals("ASOC1",RepositorioAsociaciones.getRepositorio().getAsociacion("ASOC1").getIdAsociacion());
    }

    @Test (expected = LogicRepoException.class)
    public void testGetAsociacionError() {
        Assert.assertEquals("ASOC1",RepositorioAsociaciones.getRepositorio().getAsociacion("sdadfg").getIdAsociacion());
    }

    @Test
    public void testEliminaAsociacion() {
        RepositorioAsociaciones.getRepositorio().eliminarAsociacion(asoc);
        Assert.assertEquals(0,RepositorioAsociaciones.getRepositorio().getAsociaciones().size());
    }

}