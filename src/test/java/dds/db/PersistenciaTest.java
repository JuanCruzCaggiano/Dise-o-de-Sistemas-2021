package dds.db;

import dds.domain.seguridad.usuario.Administrador;
import dds.domain.seguridad.usuario.Standard;
import org.junit.After;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.security.NoSuchAlgorithmException;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {


    @After
    public void rollBack() {
        rollbackTransaction();
    }


    @Test
    public void PullInicialDeDatos() throws NoSuchAlgorithmException {

        Administrador usuarioTest = new Administrador("usuarioTest","Password123+");


        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(usuarioTest);

        EntityManagerHelper.commit();


    }
}