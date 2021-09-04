package dds.domain.persona.transaccion;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_transaccion")
public abstract class Transaccion {

    @Id
    @GeneratedValue
    protected int idTransaccion;

    public abstract void ejecutar();
    public abstract int getIdTransaccion();


}
