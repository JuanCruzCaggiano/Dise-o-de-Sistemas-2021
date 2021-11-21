package dds.db;

import dds.domain.entities.asociacion.Asociacion;
import dds.domain.entities.mascota.Mascota;
import dds.domain.entities.mascota.Sexo;
import dds.domain.entities.mascota.TipoMascota;
import dds.domain.entities.persona.Persona;
import dds.domain.entities.persona.TipoDocumento;
import dds.domain.entities.persona.roles.Adoptante;
import dds.domain.entities.persona.roles.Duenio;
import dds.domain.entities.persona.roles.Rescatista;
import dds.domain.entities.persona.roles.Voluntario;
import dds.domain.entities.persona.transaccion.DarEnAdopcion;
import dds.domain.entities.persona.transaccion.QuieroAdoptar;
import dds.domain.entities.seguridad.usuario.Administrador;
import dds.domain.entities.seguridad.usuario.Standard;
import dds.servicios.apiHogares.Ubicacion;
import dds.servicios.avisos.Email;
import dds.servicios.avisos.FormaNotificacion;
import dds.servicios.publicaciones.PublicacionMascota;
import dds.servicios.publicaciones.TipoPublicacion;
import org.junit.After;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

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

        Asociacion asoc = new Asociacion("Rescate de Patitas CABA",new Ubicacion("Rivadavia 9450",-34.63722034233585, -58.49715981178081));
        asoc.getConfigurador().agregarCaracteristicaMascota("Color de Pelo");
        asoc.getConfigurador().agregarCaracteristicaMascota("Tamaño");

        RepositorioUsuarios.getRepositorio().crearUsuarioAdministrador("superUsuario","Password123+",asoc);




        Mascota perro = new Mascota(TipoMascota.PERRO,"lola beatriz","lo",LocalDate.now().minusYears(6),"Obejero",new ArrayList<>(),new HashMap<>(), Sexo.HEMBRA);
        Mascota perro2 = new Mascota(TipoMascota.PERRO,"Fatiga","Fatiga",LocalDate.now().minusYears(11),"Estrella de TV",new ArrayList<>(),new HashMap<>(),Sexo.MACHO);
        perro.agregarCaracteristica("Color De Pelo","Negro y Marron");
        perro.agregarCaracteristica("Tamaño","Grande");
        perro.setEstaPerdida(true);
        Email email = new Email();
        List<FormaNotificacion> formasDeNoti = new ArrayList<>();
        formasDeNoti.add(email);
        Persona persona = new Persona("Matias","Lanneponders",TipoDocumento.DNI,39000401,LocalDate.of(1995,07,07),"dire","1165485425","mlyonadi@gmail.com",formasDeNoti);
        persona.getNotificador().agendarContacto("Pedro", "Dorr", "1140435092", "dorrpei@gmail.com", formasDeNoti);
        persona.agregarMascota(perro);
        persona.agregarMascota(perro2);
        HashMap<String, String> preguntas = new HashMap<String, String>();
        preguntas.put(asoc.getConfigurador().getPreguntas().get(0),"Negro");
        preguntas.put(asoc.getConfigurador().getPreguntas().get(1),"Grande");

        RepositorioUsuarios.getRepositorio().crearUsuarioStandard("usuarioDuenio","Password123+",persona,asoc);


        Persona persona3 = new Persona("Agustin","Orlando",TipoDocumento.DNI,39000401,LocalDate.of(1999,05,30),"Por algun lugar villa crespo","1165485425","orlandoagustin00@gmail.com",formasDeNoti);
        persona3.getListaRoles().add(Voluntario.getVoluntario());
        RepositorioUsuarios.getRepositorio().crearUsuarioStandard("usuarioVoluntario","Password123+",persona3,asoc);
        Persona adoptante = new Persona("Gabriel","Figueroa",TipoDocumento.DNI,33501523,LocalDate.of(1988,07,07),"dire","1165486542","gabriel.n.figueroa@gmail.com",formasDeNoti);
        adoptante.getListaRoles().add(Adoptante.getAdoptante());
        RepositorioUsuarios.getRepositorio().crearUsuarioStandard("usuarioAdoptante","Password123+",adoptante,asoc);
        preguntas = new HashMap<String, String>();



        List <String> keys =  RepositorioAsociaciones.getRepositorio().getAsociaciones().get(0).getConfigurador().getPreguntas();
        for (int i=0;i<keys.size();i++) {
            preguntas.put(keys.get(i),"Respuesta x");
        }

        adoptante.ejecutarTransaccion(new QuieroAdoptar(adoptante.getIdPersona(),preguntas));
        //Prueba de ejecucion de tansaccion Dar en Adopcion por un usuario standard dueño.
        persona.ejecutarTransaccion(new DarEnAdopcion(perro.getIdMascota(),persona.getIdPersona(),preguntas));
    }
}