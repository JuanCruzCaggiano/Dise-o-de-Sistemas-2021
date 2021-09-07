package dds.domain.asociacion;
import dds.db.EntityManagerHelper;
import dds.db.RepositorioAsociaciones;
import dds.domain.mascota.Mascota;
import dds.domain.mascota.Sexo;
import dds.domain.mascota.TipoMascota;
import dds.domain.persona.Persona;
import dds.domain.persona.TipoDocumento;
import dds.domain.persona.roles.Duenio;
import dds.domain.seguridad.usuario.Administrador;
import dds.domain.seguridad.usuario.Standard;
import dds.domain.seguridad.usuario.Usuario;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.avisos.AdapterEmail;
import dds.servicios.avisos.AdapterFormaNotificacion;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfiguracionesTest {
    Usuario usuDuenio;
    String fotoAModif = "imgprueba.jpg";
    String fotoModif = "recorte.jpg";
    Asociacion asoc;
    Mascota mascota;
    Persona personaDuenio;

    @Before
    public void setUp() throws NoSuchAlgorithmException {

        if (EntityManagerHelper.getEntityManager().createQuery("from Asociacion").getResultList().isEmpty()){
            //CREO ASOC
            asoc = new Asociacion("Asco",new Ubicacion("DIR",0,0));
            mascota = new Mascota(TipoMascota.PERRO,"lola beatriz","lola", LocalDate.now().minusYears(6),"bruta beba",new ArrayList<>(),new HashMap<>(), Sexo.MACHO);
            //CREO DUENIO
            AdapterEmail adEmail = new AdapterEmail();
            List<AdapterFormaNotificacion> formasDeNoti = new ArrayList<>();
            formasDeNoti.add(adEmail);
            personaDuenio = new Persona("Matias", "Lanneponders",TipoDocumento.DNI,
                    39000401,LocalDate.of(1995, 7, 7),
                    "dir","1155892198", "mlyonadi@gmail.com", formasDeNoti);

            usuDuenio = new Standard("matilanne","Password1234+",personaDuenio);
            usuDuenio.setAsociacion(asoc);
            personaDuenio.agregarRol(Duenio.getDuenio());
            personaDuenio.getMascotas().add(mascota);

            EntityManagerHelper.beginTransaction();
            EntityManagerHelper.entityManager().persist(asoc);
            EntityManagerHelper.entityManager().persist(usuDuenio);
            EntityManagerHelper.commit();
            asoc.getConfiguraciones().agregarCaracteristicaMascota("Color de Pelo");
            asoc.getConfiguraciones().agregarCaracteristicaMascota("Tamaño");
        } else
        {
            asoc = (Asociacion) EntityManagerHelper.getEntityManager().createQuery("from Asociacion ").getResultList().get(0);
            mascota  = (Mascota) EntityManagerHelper.getEntityManager().createQuery("from Mascota ").getResultList().get(0);
        }
    }

    @Test
    public void testFoto() {

        asoc.getConfiguraciones().cambiarTamanio(fotoAModif,fotoModif);
    }
    @Test
    public void testAgregarCaracteristica() {
        Assert.assertEquals(2,RepositorioAsociaciones.getRepositorio().getAsociacion(asoc.getIdAsociacion()).getConfiguraciones().getClaves().size());
    }
    @Test
    public void testEliminarCaracteristica() {
        RepositorioAsociaciones.getRepositorio().getAsociacion(asoc.getIdAsociacion()).getConfiguraciones().eliminarCaracteristicas("Color de Pelo");
        Assert.assertEquals(1,RepositorioAsociaciones.getRepositorio().getAsociacion(asoc.getIdAsociacion()).getConfiguraciones().getClaves().size());
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