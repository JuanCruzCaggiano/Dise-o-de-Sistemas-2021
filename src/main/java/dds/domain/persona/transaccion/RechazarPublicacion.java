package dds.domain.persona.transaccion;

import javax.mail.MessagingException;

public class RechazarPublicacion implements Transaccion {
    final  int idTransaccion = 6;
    @Override
    public void ejecutar(){

    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
