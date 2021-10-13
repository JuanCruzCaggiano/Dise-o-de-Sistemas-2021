package dds.servicios.helpers;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class PhotoUploaderHelper {

    private static PhotoUploaderHelper dateHelper = new PhotoUploaderHelper();

    public static PhotoUploaderHelper getHelper() { return dateHelper; }

    public String uploadPhoto (InputStream ss) throws IOException {
        String foto = null;
        File uploadDir = new File("src\\main\\resources\\public\\fotos");
        uploadDir.mkdir();
        Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
        Files.copy(ss, tempFile, StandardCopyOption.REPLACE_EXISTING);
        foto = tempFile.toString();

        return foto;
    }




}
