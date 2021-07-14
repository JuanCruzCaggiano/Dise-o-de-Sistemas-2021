package dds.servicios.avisos;



import dds.db.RepositorioPersonas;
import dds.domain.mascota.Mascota;
import dds.domain.persona.Persona;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class Notificador {
    private List<Contacto> suscriptores = new ArrayList<>();
    private AdapterFormaNotificacion adapter;



    public List<Contacto> getSuscriptores() {
        return suscriptores;
    }
    //agendar
    public void agendarContacto(String nombre, String apellido, String telefono, String email, List<AdapterFormaNotificacion> formasDeNoti){
        Contacto contactoNuevo = new Contacto(nombre,apellido,telefono,email,formasDeNoti);
        suscriptores.add(contactoNuevo);

    }
    // modificar()
    public void modificarContacto(Contacto buscado,String nombre,String apellido,String telefono,String email) throws Exception {
       if (buscarContacto(buscado) == -1){
           throw new Exception("No existe dicho usuario");
       }else{
        suscriptores.get(buscarContacto(buscado)).setNombre(nombre);
        suscriptores.get(buscarContacto(buscado)).setApellido(apellido);
        suscriptores.get(buscarContacto(buscado)).setEmail(email);
        suscriptores.get(buscarContacto(buscado)).setTelefono(telefono);}
    }
    public int buscarContacto(Contacto buscado){
        for (int i=0;i<suscriptores.size();i++){
            if (buscado.getNombre() == suscriptores.get(i).getNombre()){
                return i;
            }
        }
        return -1;
    }
    // eliminar()
    public void eliminarContacto(Contacto eliminar){
        suscriptores.remove(buscarContacto(eliminar));
    }

    public void notificar(String idMascota)  {
        Persona duenio = RepositorioPersonas.getRepositorio().getPersona(RepositorioPersonas.getRepositorio().getIdPersonaXidMascota(idMascota));
        Mascota mascota = duenio.getMascota(idMascota);
        String link = "";//TODO Crear formula en un singleton servicio que genere el link que te lleve a la publicacion de la mascota encontrada.
        String mensaje = "Encontramos a "+ mascota.getNombre() + " para mas informacion ingresa al siguiente link!: " + link;
        for (int i=0;i<suscriptores.size();i++){
            List<AdapterFormaNotificacion> formas = suscriptores.get(i).getFormasNotificacion();
            for (int j=0;j<formas.size();j++) {
                formas.get(j).notificar(mensaje,suscriptores.get(i)); //aca paso el suscriptor
            }
        }
    }


    public void notificarPersona(String mensaje) {
        List<AdapterFormaNotificacion> formas = suscriptores.get(0).getFormasNotificacion();
        for (int j=0;j<formas.size();j++) {
            formas.get(j).notificar(mensaje,suscriptores.get(0)); //aca paso el suscriptor
        }
    }
}
