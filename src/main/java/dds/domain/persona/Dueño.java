package dds.domain.persona;

import dds.domain.asociacion.ConfigCaracMascota;
import dds.domain.mascota.Mascota;
import dds.domain.mascota.TipoMascota;

import java.util.List;

public class Dueño implements RolPersona {

    @Override
    public void realizarTransaccion(String algo) {

    }

    //se le pasa la lista de la persona y los demas parametros
    private void registrarMascota(List<Mascota> lista, TipoMascota tipo, String nombre, String apodo, Integer edad, String descripcion, List<String> listaFotos, List<ConfigCaracMascota> caracteristica, Boolean estaPerdida){
        Mascota nueva = new Mascota(tipo,nombre,apodo,edad,descripcion,listaFotos,caracteristica,estaPerdida);
        lista.add(nueva);
    }
    private void encontreMiMascota(Mascota mascota){
        mascota.setEstaPerdida(false);
        //faltaria el metodo que agregue a esta mascota en la lista de mascotas encontradas
    }
}