package dds.db;

import dds.domain.mascota.Mascota;
import dds.domain.mascota.TipoMascota;
import dds.servicios.apiHogares.ComunicarApi;
import dds.servicios.apiHogares.HogarDeTransito;
import dds.servicios.avisos.Notificador;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;




public class RepositorioHogaresDeTransitoTest {
    String mail = "dorrpei@gmail.com";
    String token= "vZ1FyLA96SztFwBa0EyApB9qS5EGqfcsyQDzaNxPi8OZJXA1GqqixFx3XRYM";

    RepositorioHogaresDeTransito repositorioHogaresDeTransito;
    ComunicarApi comunicarApi = new ComunicarApi();

    @Before
    public void setUp() throws Exception {
        RepositorioHogaresDeTransito.getRepositorio().getHogares().clear(); //arranca con la lista de hogares limpia


    }

    @Test //Se prueba que haya 9/10 (offset 1 ---> rango 1-10) hogares de tránsito que acepten perros
    public void testFiltrarHogaresQueAceptanPerros() {
        String res = comunicarApi.obtenerHogares(1, token);
        Assert.assertEquals(9, RepositorioHogaresDeTransito.getRepositorio().filtrarPorTipoDeAnimal(TipoMascota.PERRO).size());
    }

    @Test //Se prueba que haya 9/10 (offset 2 ---> rango 11-20) hogares de tránsito que acepten gatos
    public void testFiltrarHogaresQueAceptanGatos() {
        String res = comunicarApi.obtenerHogares(2, token);
        Assert.assertEquals(9, RepositorioHogaresDeTransito.getRepositorio().filtrarPorTipoDeAnimal(TipoMascota.GATO).size());
    }

    @Test //Se prueba que haya 3/10 (offset 3 ---> rango 21-30) hogares de tránsito que acepten tanto perros como gatos
    public void testFiltrarHogaresQueAceptanTantoPerrosComoGatos() {
        String res = comunicarApi.obtenerHogares(3, token);
        Assert.assertEquals(3, RepositorioHogaresDeTransito.getRepositorio().filtrarPorAmbosTipoDeAnimal().size());
    }



    @Test //Se prueba que haya 6/10 (offset 4 ---> rango 31-40) hogares de tránsito que tengan patio
    public void testFiltrarHogaresQueTienenPatio() {
        String res = comunicarApi.obtenerHogares(4, token);
        Assert.assertEquals(6, RepositorioHogaresDeTransito.getRepositorio().filtrarPorPatio().size());
    }



    @Test //Se prueba que haya 10/10 (offset 1 ---> rango 1-10) hogares de tránsito que tienen lugares disponibles
    public void testFiltrarHogaresQueTienenLugaresDisponibles() {
        String res = comunicarApi.obtenerHogares(1, token);
        Assert.assertEquals(10, RepositorioHogaresDeTransito.getRepositorio().filtrarPorDisponibilidad().size());
    }



    @Test //Se prueba que haya 7/10 (offset 1 ---> rango 1-10) hogares de tránsito que no tengan ninguna característica
    public void testFiltrarHogaresQueNoTienenNingunaCaracterística() {
        String res = comunicarApi.obtenerHogares(1, token);
        List<String> caracteristicas = new ArrayList<>();
        Assert.assertEquals(7, RepositorioHogaresDeTransito.getRepositorio().filtrarPorCaracteristica(caracteristicas).size());
    }

    @Test //Se prueba que haya 3/10 (offset 2 ---> rango 11-20) hogares de tránsito que tengan como característica a "manso"
    public void testFiltrarHogaresQueTenganComoCaracteristicaManso() {
        String res = comunicarApi.obtenerHogares(2, token);
        List<String> caracteristicas = new ArrayList<>();
        caracteristicas.add("Manso");
        Assert.assertEquals(3, RepositorioHogaresDeTransito.getRepositorio().filtrarPorCaracteristica(caracteristicas).size());
    }

    @Test //Se prueba que haya 2/10 (offset 2 ---> rango 11-20) hogares de tránsito que tengan como características a "delgado" y a "amistoso"
    public void testFiltrarHogaresQueTenganComoCaracteristicasDelgadoYAmistoso() {
        String res = comunicarApi.obtenerHogares(2, token);
        List<String> caracteristicas = new ArrayList<>();
        caracteristicas.add("Delgado");
        caracteristicas.add("Amistoso");
        Assert.assertEquals(2, RepositorioHogaresDeTransito.getRepositorio().filtrarPorCaracteristica(caracteristicas).size());
    }


    @Test   //Se prueba el radio de cercanía desde un mismo hogar de tránsito (el de id=34, de Río Gallegos, Santa Cruz)
            // cuyas coordenadas son:   lat    -51.622855315759274
            //                          long   -69.21685055962318
    public void testFiltrarPorRadioDeCercania() {
        //TODO --> REVISAR --> la longitud no coincide con la clave/key del JSON
        /*
        String res = comunicarApi.obtenerHogares(4, token);
        double la = -51.622855315759274;
        double lo = -69.21685055962318;
        double rad = 500;
        Assert.assertEquals(1, RepositorioHogaresDeTransito.getRepositorio().filtrarPorDistancia(la, lo, rad).size());
        */
    }

}
