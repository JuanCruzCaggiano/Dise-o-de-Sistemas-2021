package dds.domain.controllers;

import dds.db.RepositorioMascotas;
import dds.db.RepositorioUsuarios;
import dds.domain.entities.asociacion.Asociacion;
import dds.domain.entities.mascota.Mascota;
import dds.domain.entities.mascota.Sexo;
import dds.domain.entities.mascota.TipoMascota;
import dds.domain.entities.persona.TipoDocumento;
import dds.domain.entities.persona.transaccion.RegistrarMascota;
import dds.domain.entities.seguridad.usuario.Standard;
import dds.domain.entities.seguridad.usuario.Usuario;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.io.IOUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static spark.Spark.staticFiles;

public class ControllerRegistroMascota {
    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public ControllerRegistroMascota() {
    }
    private static String convertInputStreamToString(InputStream is) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        // Java 1.1
        return result.toString(StandardCharsets.UTF_8.name());

    }
    public Response agregarFoto(Request request, Response response) throws NoSuchAlgorithmException, IOException, ServletException {
        Map<String, Object> parametros = new HashMap<>();
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        String id = request.params("id");
        Usuario usuario = request.session().attribute("usuario");
        Mascota mascota = RepositorioMascotas.getRepositorio().getMascota(id);
        String foto = null;
        InputStream ss = request.raw().getPart("foto").getInputStream();
        byte[] bytes = IOUtils.toByteArray(ss);
        foto = Base64.getEncoder().encodeToString(bytes);
        mascota.agregarfoto(foto);
        response.redirect("/");
        return response;
    }
    public Response registrarMascota(Request request, Response response) throws NoSuchAlgorithmException, IOException {
        Usuario usuario = request.session().attribute("usuario");
        Asociacion asoc = usuario.getAsociacion();
        List<String> preguntasCaracs = asoc.getConfigurador().getClaves();
        HashMap <String, String> caracteristica = new HashMap<>();
        File uploadDir = new File("upload");


        String tipo=null;
        String nombre=null;
        String desc=null;
        String apodo=null;
        String sexo = null;
        String caracs=null;
        String foto=null;
        String fecha = null;
        List<String> fotos = new ArrayList<>();
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
        try (InputStream input = request.raw().getPart("nombre").getInputStream()) { // getPart needs to use same "name" as input field in form

            String resultado = convertInputStreamToString(input);
            nombre = resultado;
            InputStream ss = request.raw().getPart("tipo").getInputStream();
            tipo=convertInputStreamToString(ss);
            ss = request.raw().getPart("apodo").getInputStream();
            apodo = convertInputStreamToString(ss);
            ss = request.raw().getPart("desc").getInputStream();
            desc = convertInputStreamToString(ss);
            ss = request.raw().getPart("sexo").getInputStream();
            sexo = convertInputStreamToString(ss);
            for (int i =0 ;i<preguntasCaracs.size();i++){
                ss = request.raw().getPart(preguntasCaracs.get(i)).getInputStream();
                caracteristica.put(preguntasCaracs.get(i),convertInputStreamToString(ss));
            }
            ss = request.raw().getPart("fecha").getInputStream();
            fecha = convertInputStreamToString(ss);
            ss = request.raw().getPart("foto").getInputStream();
            Files.copy(ss, tempFile, StandardCopyOption.REPLACE_EXISTING);
            /*byte[] bytes = IOUtils.toByteArray(ss);
            File targetFile = new File("/upload/targetFile.tmp");
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(bytes);
            foto = Base64.getEncoder().encodeToString(bytes);*/
        } catch (ServletException e) {
            e.printStackTrace();
        }
        foto = tempFile.toString();
        Sexo sexoEnum= Sexo.valueOf(sexo);
        TipoMascota tipoEnum = TipoMascota.valueOf(tipo);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dt = LocalDate.parse(fecha,dtf);
        fotos.add(foto);


        //Mascota mascota = new Mascota(tip,nombre,apodo,dt,desc,fotos,caracteristica,sex);
        usuario.getPersona().ejecutarTransaccion(new RegistrarMascota(usuario.getPersona(),tipoEnum,nombre,apodo,dt,desc,fotos,caracteristica,sexoEnum));

        Map<String,Object> parametros = new HashMap<>();

        response.redirect("/panel#registroConExito");  //hay que ver como era el redirect
        return response;
    }
}
