package dds.domain.persona;

import dds.domain.mascota.Mascota;
import dds.servicios.avisos.Notificador;


import java.time.LocalDate;
import java.util.List;

public class Persona {
    private String nombre;
    private String apellido;
    private List<Mascota> mascotas;
    private LocalDate fechaNac;
    private TipoDocumento tipoDoc;
    private Integer nroDoc;
    private List<RolPersona> listaRoles;
    private Notificador notificador;

}
