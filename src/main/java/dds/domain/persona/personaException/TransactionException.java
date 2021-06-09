package dds.domain.persona.personaException;

public class TransactionException extends RuntimeException{

    public TransactionException(String mensajeDeError) {
        System.out.println(mensajeDeError);
    }

}
