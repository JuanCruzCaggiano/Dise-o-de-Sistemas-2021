package dds.domain.persona.personaException;

public class AssignPersonaException extends RuntimeException{

    public AssignPersonaException(String mensajeDeError) {
        System.out.println(mensajeDeError);
    }

}
