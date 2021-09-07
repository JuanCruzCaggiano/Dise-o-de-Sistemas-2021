package dds.db;

import dds.db.repositorioException.LogicRepoException;
import dds.domain.asociacion.Asociacion;
import dds.servicios.apiHogares.Ubicacion;
import junit.framework.TestCase;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepositorioAsociacionesTest {

    Asociacion asoc;

    @Before
    public void setUp(){

        asoc = new Asociacion("Asociacion",new Ubicacion("Rivadavia 3350",63.584865,63.25186));


    }

    @Test
    public void ApersistenciaTest(){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(asoc);
        EntityManagerHelper.commit();

    }

    @Test
    public void BtestGetAsociacion() {
        Asociacion asociacion = (Asociacion) EntityManagerHelper.entityManager().createQuery("FROM Asociacion ").getResultList().get(0);

        Assert.assertEquals(asociacion.getIdAsociacion(),RepositorioAsociaciones.getRepositorio().getAsociacion(asociacion.getIdAsociacion()).getIdAsociacion());
    }

    @Test (expected = LogicRepoException.class)
    public void CtestGetAsociacionError() {
        Asociacion asociacion = (Asociacion)  EntityManagerHelper.entityManager().createQuery("FROM Asociacion ").getResultList().get(0);

        Assert.assertEquals(asociacion,RepositorioAsociaciones.getRepositorio().getAsociacion(1568).getIdAsociacion());
    }

    @Test
    public void DtestEliminaAsociacion() {
        Asociacion asocAEliminar = (Asociacion) EntityManagerHelper.entityManager().createQuery("FROM Asociacion ").getResultList().get(0);
        RepositorioAsociaciones.getRepositorio().eliminarAsociacion(asocAEliminar);
        Assert.assertEquals(0,RepositorioAsociaciones.getRepositorio().getAsociaciones().size());
    }

}