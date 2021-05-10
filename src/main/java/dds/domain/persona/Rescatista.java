package dds.domain.persona;

import dds.domain.mascota.Mascota;

import javax.mail.MessagingException;

public class Rescatista implements RolPersona {

    @Override
    public void realizarTransaccion(String algo) {

    }

    public void encontreMascotaPerdida(Mascota mascota, RepositorioPersonas repo) throws MessagingException { //esto busca al dueño y dentro del repo se encarga de notificar
        Integer identificador = repo.tieneMascota(mascota);
        repo.getRepoPersonas().get(identificador).getNotificador().notificar("encontramos a "+mascota.getNombre());
    }
    private void buscarHogarDeTransito(){ //TODO falta implementar

    }
}
