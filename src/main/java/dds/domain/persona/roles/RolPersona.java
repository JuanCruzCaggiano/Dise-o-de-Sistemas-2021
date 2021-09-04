package dds.domain.persona.roles;

import dds.domain.persona.personaException.TransactionException;
import dds.domain.persona.transaccion.Transaccion;

import javax.mail.MessagingException;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TransferQueue;

@Entity
@Table(name = "rolpersona")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol")
public abstract class RolPersona {
    @Id
    @GeneratedValue
    private int id;

    @Column
    protected String nombre;

    @OneToMany(cascade = {CascadeType.ALL})
    List<Transaccion> permisos = new ArrayList<>();

     public void ejecutarTransaccion(Transaccion transaccion)  {
        if(this.permisos.stream().anyMatch(p -> p.getIdTransaccion() ==(transaccion.getIdTransaccion()))){
            transaccion.ejecutar();
        }else{
            throw new TransactionException("No posee los permisos para ejecutar la transaccion");
        }
    }

    public String getNombre() {
        return nombre;
    }
}
