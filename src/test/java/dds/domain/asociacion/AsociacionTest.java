package dds.domain.asociacion;

import dds.db.RepoAsociacion;
import org.junit.Assert;
import org.junit.Test;






public class AsociacionTest {
    RepoAsociacion repo=new RepoAsociacion();
    Asociacion asoc= new Asociacion("a","b","c","d","e","f");


    @Test
    public void validarAgregarAsoc() {
        asoc.agregarAsociacion("a","b","c","d","e","f",repo);
        Assert.assertEquals(asoc.getNombre(),repo.getRepositorio().get(repo.buscarAsociacion(asoc)).getNombre());
    }
    @Test
    public void validarRemover() {
        asoc.agregarAsociacion("a","b","c","d","e","f",repo);
        asoc.quitarAsociacion(asoc,repo);
        Assert.assertEquals(0,repo.getRepositorio().size());
    }
}
