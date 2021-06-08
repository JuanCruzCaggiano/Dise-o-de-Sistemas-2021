package dds.domain.persona.roles;

import dds.domain.persona.transaccion.Transaccion;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.concurrent.TransferQueue;

public interface RolPersona {

     void ejecutarTransaccion(Transaccion transaccion) ;


}
