package dds.db;

import dds.domain.mascota.TipoMascota;
import dds.servicios.apiHogares.ServicioHogarDeTransito;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;




public class RepositorioHogaresDeTransitoTest {
    String mail = "dorrpei@gmail.com";
    String token= "vZ1FyLA96SztFwBa0EyApB9qS5EGqfcsyQDzaNxPi8OZJXA1GqqixFx3XRYM";

    RepositorioHogaresDeTransito repositorioHogaresDeTransito;
    ServicioHogarDeTransito servicioHogarDeTransito = new ServicioHogarDeTransito();

    @Before
    public void setUp() {
        RepositorioHogaresDeTransito.getRepositorio().getHogares().clear(); //arranca con la lista de hogares limpia
    }

    @Test //Se prueba que 28/40 hogares de tránsito aceptan perros
    public void testFiltrarHogaresQueAceptanPerros() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        Assert.assertEquals(28, RepositorioHogaresDeTransito.getRepositorio().filtrarPorTipoDeAnimal(TipoMascota.PERRO).size());
    }

    @Test //Se prueba que 30/40 hogares de tránsito aceptan gatos
    public void testFiltrarHogaresQueAceptanGatos() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        Assert.assertEquals(30, RepositorioHogaresDeTransito.getRepositorio().filtrarPorTipoDeAnimal(TipoMascota.GATO).size());
    }

    @Test //Se prueba que 18/40 hogares de tránsito aceptan tanto perros como gatos
    public void testFiltrarHogaresQueAceptanTantoPerrosComoGatos() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        Assert.assertEquals(18, RepositorioHogaresDeTransito.getRepositorio().filtrarPorAmbosTipoDeAnimal().size());
    }

    @Test //Se prueba que 25/40 hogares de tránsito tienen patio
    public void testFiltrarHogaresQueTienenPatio() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        Assert.assertEquals(26, RepositorioHogaresDeTransito.getRepositorio().filtrarPorPatio().size());
    }



    @Test //Se prueba que 40/40 hogares de tránsito tienen disponibilidad (lugares disponibles) para poder aceptar mascotas
    public void testFiltrarHogaresQueTienenLugaresDisponibles() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        Assert.assertEquals(40, RepositorioHogaresDeTransito.getRepositorio().filtrarPorDisponibilidad().size());
    }



    @Test //Se prueba que 28/40 hogares de tránsito no tienen ninguna característica en particular
    public void testFiltrarHogaresQueNoTienenNingunaCaracteristica() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        List<String> caracteristicas = new ArrayList<>();
        Assert.assertEquals(28, RepositorioHogaresDeTransito.getRepositorio().filtrarPorCaracteristica(caracteristicas).size());
    }

    @Test //Se prueba que 9/40 hogares de tránsito tienen "manso" como característica
    public void testFiltrarHogaresQueTenganComoCaracteristicaManso() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        List<String> caracteristicas = new ArrayList<>();
        caracteristicas.add("Manso");
        Assert.assertEquals(9, RepositorioHogaresDeTransito.getRepositorio().filtrarPorCaracteristica(caracteristicas).size());
    }

    @Test //Se prueba que 7/40 hogares de tránsito tienen "delgado" y "amistoso" como características
    public void testFiltrarHogaresQueTenganComoCaracteristicaDelgadoYAmistoso() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        List<String> caracteristicas = new ArrayList<>();
        caracteristicas.add("Delgado");
        caracteristicas.add("Amistoso");
        Assert.assertEquals(7, RepositorioHogaresDeTransito.getRepositorio().filtrarPorCaracteristica(caracteristicas).size());
    }



    @Test   //Se prueba que hay un único hogar de tránsito que está a menos de 1 metro del punto indicado
    public void testFiltrarPorRadioDeCercania1m() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        double la = -51.622855315759274;    // Dato del hogar de tránsito ubicado en Río Gallegos, Santa Cruz (id=34)
        double lo = -69.21685055962318;     // Dato del hogar de tránsito ubicado en Río Gallegos, Santa Cruz (id=34)
        double rad = 0.001;                 // rad está en kilómetros ----> 0.001 kilómetros = 1 metro
        Assert.assertEquals(1, RepositorioHogaresDeTransito.getRepositorio().filtrarPorDistancia(la, lo, rad).size());
    }

    @Test   //Se prueba que hay un único hogar de tránsito que está a menos de 1000 kilómetros desde el punto indicado (este hogar de tránsito está muuuuy lejos del resto de los hogares)
    public void testFiltrarPorRadioDeCercania1000km() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        double la = -51.622855315759274;    // Dato del hogar de tránsito ubicado en Río Gallegos, Santa Cruz (id=34)
        double lo = -69.21685055962318;     // Dato del hogar de tránsito ubicado en Río Gallegos, Santa Cruz (id=34)
        double rad = 1000;                 // rad está en kilómetros ----> 0.001 kilómetros = 1 metro
        Assert.assertEquals(1, RepositorioHogaresDeTransito.getRepositorio().filtrarPorDistancia(la, lo, rad).size());
    }

    @Test   //Se prueba que hay un único hogar de tránsito que está a menos de 1000 kilómetros desde el punto indicado (este hogar de tránsito está muuuuy lejos del resto de los hogares)
    public void testFiltrarPorRadioDeCercania1000000km() {
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        double la = -51.622855315759274;    // Dato del hogar de tránsito ubicado en Río Gallegos, Santa Cruz (id=34)
        double lo = -69.21685055962318;     // Dato del hogar de tránsito ubicado en Río Gallegos, Santa Cruz (id=34)
        double rad = 1000000;                 // rad está en kilómetros ----> 0.001 kilómetros = 1 metro
        Assert.assertEquals(40, RepositorioHogaresDeTransito.getRepositorio().filtrarPorDistancia(la, lo, rad).size());
    }



    @Test   //Se prueba se traen todos los hogares de tránsito (son 40 en total)
    public void actualizarRepositorioHogaresDeTransitoTest(){
        servicioHogarDeTransito.actualizarRepositorioHogaresDeTransito();
        Assert.assertEquals(40,RepositorioHogaresDeTransito.getRepositorio().getHogares().size());
    }
}