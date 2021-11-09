package dds.servicios.helpers;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class QRHelper
{

    private static QRHelper qrHelper = new QRHelper();

    public static QRHelper getHelper() { return qrHelper; }

    //calculo de distancia
    public  static void creadorQR(String data, String pathname)
    {
        int ancho = 400;
        int alto = 400;

        String formatoImagen = "png";
        BitMatrix bitMatrix = new QRCodeWriter().enconde(data, BarcodeFormat.QR_CODE, ancho, alto);
        FileOutputStream outputStream = new FileOutputStream(pathname);
    }

    public static String leerQR(String pathname) {
        InputStream qrInputStream = new FileInputStream(pathname);
        BufferedImage qrBufferedImage = ImageIO.read(qrInputStream);

        LuminanceSource source = new BufferedImageLuminanceSource(qrBufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Reader reader = new MultiFormatReader();
        Result stringBarCode = reader.decode(bitmap);
    }







}
