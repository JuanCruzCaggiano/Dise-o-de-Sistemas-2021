package dds.servicios.avisos;

import dds.db.RepositorioPersonas;
import dds.domain.asociacion.Asociacion;
import dds.domain.persona.Persona;
import dds.servicios.publicaciones.PublicacionAdopcion;
import dds.servicios.publicaciones.PublicacionQuieroAdoptar;
import dds.db.RepositorioAsociaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PreferenciasDeAdopcion {
    private List<PublicacionQuieroAdoptar> posiblesAdoptantes = new ArrayList<>();
    private List<PublicacionAdopcion> publicacionesParaAdoptar = new ArrayList<>();
    private List<Asociacion> asociaciones = new ArrayList<>();
    private List<Persona> personas = new ArrayList<>();
    RepositorioPersonas repositorioPersonas;
    RepositorioAsociaciones repositorioAsociaciones;


    public List<PublicacionQuieroAdoptar> obtenerPublicacionesAdoptantesSegunAsociacion(String idAsoc) {
        Asociacion asociacion;
        asociacion = repositorioAsociaciones.getRepositorio().getAsociacion(idAsoc);

        return asociacion.getPublicador().getPublicacionesQuieroAdoptar();
    }

    public List<PublicacionAdopcion> obtenerPublicacionesEnAdopcionSegunAsociacion(String idAsoc) {
        Asociacion asociacion;
        asociacion = repositorioAsociaciones.getRepositorio().getAsociacion(idAsoc);

        return asociacion.getPublicador().getEnAdopcion();
    }

    public List<String> obtenerPreguntasSegunAsociacion(String idAsoc) {
        Asociacion asociacion;
        asociacion = repositorioAsociaciones.getRepositorio().getAsociacion(idAsoc);

        return asociacion.getConfiguraciones().getPreguntas();
    }

    public int obtenerCantidadDeCoincidencia(int coincidenciasMinima, PublicacionQuieroAdoptar publicacionQuieroAdoptarseAux, List<PublicacionAdopcion> publicacionAdopcionesAux) {
        int cantidad = 0;
        String valor = "";
        HashMap<String, Object> preguntasPosibleAdoptante = new HashMap<String, Object>();
        preguntasPosibleAdoptante = publicacionQuieroAdoptarseAux.getPreguntas();
        List<PublicacionAdopcion> auxPubli = new ArrayList<>();
        List<PublicacionAdopcion> iteradorpublicacionAdopciones;

        for (String key : preguntasPosibleAdoptante.keySet()) {
            valor = (String) preguntasPosibleAdoptante.get(key);
            if (cantidad <= coincidenciasMinima) {
                String finalValor = valor;
                auxPubli = publicacionAdopcionesAux.stream().filter(p -> p.getPreguntas().containsKey(key)).collect(Collectors.toList()).stream().filter(pe-> pe.getPreguntas().containsValue(finalValor)).collect(Collectors.toList());
            }
            publicacionAdopcionesAux.clear();
            //cargo las nuevas publicaciones para filtrar nuevamente
            publicacionAdopcionesAux.addAll(auxPubli);


        }
        return cantidad;
    }

}
