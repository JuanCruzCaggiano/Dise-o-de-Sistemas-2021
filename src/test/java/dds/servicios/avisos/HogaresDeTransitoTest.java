package dds.servicios.avisos;

import dds.db.RepoHogaresDeTransito;
import dds.servicios.apiHogares.ComunicarApi;
import org.junit.Assert;
import org.junit.Test;

public class HogaresDeTransitoTest {
        String mail = "dorrpei@gmail.com";
        String token= "vZ1FyLA96SztFwBa0EyApB9qS5EGqfcsyQDzaNxPi8OZJXA1GqqixFx3XRYM";

        RepoHogaresDeTransito  repoHogaresDeTransito;
        ComunicarApi comunicarApi = new ComunicarApi();


        @Test
        public void usuarioYaIngresadoTest(){
            String res;
            res = comunicarApi.RegistrarEmail(mail);
            Assert.assertEquals("UsuarioYaIngresado",res);
        }


        @Test
        public void mailMalIngresadoTest(){
            String res;
            String mail = "gabi@example.com";
            res = comunicarApi.RegistrarEmail(mail);
            Assert.assertEquals("MailInvalido",res);
        }

        @Test
        public void usuarioNoAutorizadoTest(){
            String res;
            res = comunicarApi.obtenerHogares(2,"token");
            Assert.assertEquals("Unauthenticated.",res);
        }

        @Test
        public void obtenerHogaresTest(){
            String res;
            String nombreHogar;

            res = comunicarApi.obtenerHogares(1,token);
            nombreHogar = repoHogaresDeTransito.getInstance().getRepositorio().get(0).getNombre();
            Assert.assertEquals("Pensionado de mascotas \"Como en casa\"",nombreHogar);
        }
}
