package dds.domain.persona.transaccion;


import dds.db.RepositorioAsociaciones;
import dds.db.RepositorioPersonas;
import dds.domain.asociacion.Asociacion;
import dds.domain.persona.Persona;
import dds.servicios.publicaciones.PublicacionMascota;

public class EncontreMiMascota implements Transaccion {
    final  int idTransaccion = 5;
    String idPublicacion;
    String idAsociacion;
    String idDuenio;

    @Override
    public void ejecutar(){
        Asociacion asoc =  RepositorioAsociaciones.getRepositorio().getAsociacion(idAsociacion);
        PublicacionMascota publi =  asoc.getPublicador().getAprobadaXId(idPublicacion);
        Persona rescatista = RepositorioPersonas.getRepositorio().getPersona(publi.getIdRescatista());
        Persona duenio = RepositorioPersonas.getRepositorio().getPersona(idDuenio);
        String datosDuenio = "Nombre: " + duenio.getNombre()+"\n"+
                             "Apellido: "+ duenio.getApellido() +"\n"+
                             "Tipo y Nro Doc: "+ duenio.getTipoDoc() +" - "+duenio.getNroDoc()+"\n"+
                             "Fecha de Nacimiento: "+ duenio.getFechaNac().toString() +"\n"+
                             "Direccion: "+ duenio.getDireccion()+"\n"+
                             "Telefono: "+ duenio.getTelefono()+"\n"+
                             "Mail: "+ duenio.getEmail();
        String link = "www.link.com"; //TODO Link a la publicacion
        String mensaje = "Encontramos al dueño de la publicacion: " + link + "\n" +
                         "Los datos del dueño son: " + "\n" +
                          datosDuenio;
        rescatista.getNotificador().notificarPersona(mensaje);
    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }

    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public EncontreMiMascota() {
    }
    //CONSTRUCTOR PARA REALIZAR TRANSACCION
    public EncontreMiMascota(String idPublicacion,String idAsociacion,String idDuenio) {
        this.idPublicacion = idPublicacion;
        this.idAsociacion = idAsociacion;
        this.idDuenio = idDuenio;
    }
}
