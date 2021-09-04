package dds.db;

import dds.domain.mascota.Mascota;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;
import dds.domain.persona.TipoDocumento;
import dds.domain.persona.roles.Duenio;
import dds.domain.persona.roles.Rescatista;
import dds.domain.persona.roles.RolPersona;
import dds.domain.seguridad.usuario.Administrador;
import dds.domain.seguridad.usuario.Standard;
import dds.servicios.avisos.AdapterEmail;
import dds.servicios.avisos.AdapterFormaNotificacion;
import dds.servicios.avisos.Notificador;
import org.junit.After;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.management.DynamicMBean;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {


    @After
    public void rollBack() {
        rollbackTransaction();
    }


    @Test
    public void PullInicialDeDatos() throws NoSuchAlgorithmException {

        Administrador usuarioTest = new Administrador("usuarioTest","Password123+");

        Mascota perro = new Mascota(TipoMascota.PERRO,"nombrePerro","apodoPerro",5,"Pelo largo",new ArrayList<>(),new HashMap<>());
        Mascota gato = new Mascota(TipoMascota.GATO,"nombreGato","apodoGato",8,"Siames",new ArrayList<>(),new HashMap<>());

        AdapterEmail adEmail = new AdapterEmail();
        List<AdapterFormaNotificacion> formasDeNoti = new ArrayList<>();
        formasDeNoti.add(adEmail);

        Persona persona = new Persona("npersona","apersona",TipoDocumento.DNI,39000401,LocalDate.of(1995,07,07),"dire","1165485425","mail@gmail.com",formasDeNoti);
        persona.agregarRol(new Duenio());
        Persona persona2 = new Persona("agus","orlando",TipoDocumento.DNI,43031203,LocalDate.of(2000,11,03),"dire","1165485425","mail@gmail.com",formasDeNoti);
        persona2.agregarRol(new Duenio());
        persona2.agregarRol(new Rescatista());
        persona.getNotificador().agendarContacto("Matias", "Lanneponders", "1155892198", "mlyonadi@gmail.com", formasDeNoti);
        persona.getNotificador().agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti);
        persona.getMascotas().add(perro);
        persona.getMascotas().add(gato);

        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(usuarioTest);

        EntityManagerHelper.getEntityManager().persist(persona);

        EntityManagerHelper.getEntityManager().persist(persona2);

        EntityManagerHelper.commit();


    }
}