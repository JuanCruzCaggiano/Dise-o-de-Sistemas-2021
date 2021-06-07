package dds.servicios.avisos;

import dds.servicios.apiHogares.ComunicarApi;
import org.junit.Assert;
import org.junit.Test;

public class HogaresDeTransitoTest {
        String mail = "dorrpei@gmail.com";
        String token= "vZ1FyLA96SztFwBa0EyApB9qS5EGqfcsyQDzaNxPi8OZJXA1GqqixFx3XRYM";
        ComunicarApi comunicarApi = new ComunicarApi();

        @Test
        public void usuarioYaIngresadoTest(){
            String res;
            res = comunicarApi.RegistrarEmail(mail);
            Assert.assertEquals("UsuarioYaIngresado",res);
        }

        @Test
        public void obtenerHogaresTest(){
            String res;
            res = comunicarApi.obtenerHogares(2,token);
        }
}
