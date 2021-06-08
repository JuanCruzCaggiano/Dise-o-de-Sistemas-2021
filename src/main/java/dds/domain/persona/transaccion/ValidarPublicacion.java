package dds.domain.persona.transaccion;

import javax.mail.MessagingException;

public class ValidarPublicacion implements Transaccion {
    final  int idTransaccion = 8;
    @Override
    public void ejecutar()  {

    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
