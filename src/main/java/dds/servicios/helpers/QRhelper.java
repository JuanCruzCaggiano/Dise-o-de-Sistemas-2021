package dds.servicios.helpers;

import com.google.zxing.qrcode.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
public class QRHelper {
    private static QRHelper qrHelper = new QRHelper();

    public static QRHelper getHelper() {
        return qrHelper;
    }

    public static void creadorQR(String data, String pathname) throws WriterException {
        int ancho = 400;
        int alto = 400;

        String formatoImagen = "png";
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrWriter.encode(data, BarcodeFormat.QR_CODE, ancho, alto);
        //MatrixToImageWriter.writeToPath(bitMatrix, formatoImagen, pathname);

    }
}