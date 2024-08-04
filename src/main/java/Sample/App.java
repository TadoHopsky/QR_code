package Sample;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        String qrCodeText = setLinkToQR();
        String filePath = "/Users/yeezus/Desktop/Dev/TOODOO list/QRg_enerator/QR_codes/QRCode.png";
        int size = 250;
        String fileType = "png";
        File qrFile = new File(filePath);
        try {
            createQRImage(qrFile, qrCodeText, size, fileType);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    public static String setLinkToQR(){
        System.out.print("Enter you link : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType) throws WriterException, IOException {
        // Устанавливаем параметры
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(EncodeHintType.MARGIN, 1);
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        // Создаем QR код
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        Path path = FileSystems.getDefault().getPath(qrFile.getAbsolutePath());
        MatrixToImageWriter.writeToPath(byteMatrix, fileType, path);
        System.out.println("QR Code created successfully.");
    }
}
