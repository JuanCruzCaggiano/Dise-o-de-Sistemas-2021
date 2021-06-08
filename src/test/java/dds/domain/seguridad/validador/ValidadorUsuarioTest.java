package dds.domain.seguridad.validador;

import dds.db.RepositorioUsuarios;
import dds.domain.seguridad.usuario.Usuario;
import dds.domain.seguridad.usuario.usuarioException.WrongLoginException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ValidadorUsuarioTest {


        Usuario usuarioCreado;

        @Before
        public void setup() throws NoSuchAlgorithmException {
            RepositorioUsuarios.getRepositorio().getUsuarios().clear();
            usuarioCreado = new Usuario("usuarioTest","Password123+");
            RepositorioUsuarios.getRepositorio().agregarUsuario(usuarioCreado);
        }

        @Test
        public void testValidarIdentidad() throws NoSuchAlgorithmException {

            String username = usuarioCreado.getUserName();
            Assert.assertTrue(ValidadorUsuario.getValidadorUsuario().validarIdentidad(username,"Password123+"));
            //En validarIdentidad la contraseña que recibe dicho método es la que ingresa el usuario
            //y la que se persiste en el repositorio se encuentra hasheada, por lo tanto la recibe
            //en una cadena.
        }
        @Test (expected = WrongLoginException.class)
        public void testValidarIdentidadErrorContraseniaNoCoincide() throws NoSuchAlgorithmException{
            String username = usuarioCreado.getUserName();
            ValidadorUsuario.getValidadorUsuario().validarIdentidad(username,"sarasa");
        }
        @Test (expected = WrongLoginException.class)
        public void testValidarIdentidadUsuarioInexistente() throws NoSuchAlgorithmException{
            ValidadorUsuario.getValidadorUsuario().validarIdentidad("pepito","");
        }
        @Test (expected = WrongLoginException.class)
        public void testValidarIdentidadUsuarioBloqueado() throws NoSuchAlgorithmException{
            usuarioCreado.bloquear();
            ValidadorUsuario.getValidadorUsuario().validarIdentidad(usuarioCreado.getUserName(),"Password123+");

        }
        @Test (expected = WrongLoginException.class)
        public void testValidarContraseniaVencida() throws NoSuchAlgorithmException {
            RepositorioUsuarios.getRepositorio().getUsuario("usuarioTest").setLastPasswordDT(LocalDateTime.now(ZoneOffset.UTC).minusDays(31));
            ValidadorUsuario.getValidadorUsuario().validarIdentidad(usuarioCreado.getUserName(),"Password123+");
        }


}