package com.example.scan;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class QRCodeGeneratorService {

    public static void generateCustomQR(String data, int size, Color qrColor,Color bgColor, int borderRadius, String filePath)
            throws WriterException, IOException {
        BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size,
                Map.of(EncodeHintType.CHARACTER_SET, "UTF-8"));

        BufferedImage qrImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = qrImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill background color
        g.setColor(bgColor);
        g.fillRect(0, 0, size, size);

        // Draw QR code with rounded corners
        g.setColor(qrColor);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (bitMatrix.get(x, y)) {
                    g.fill(new RoundRectangle2D.Float(x, y, 1, 1, borderRadius, borderRadius));
                }
            }
        }
        g.dispose();
        //save QRImage
        ImageIO.write(qrImage, "png", new File(filePath));
        System.out.println("QR Code generated: " + filePath);
    }

    // uses google's zxing-library
    public static String decodeQR(String filePath) throws IOException, NotFoundException {
        File qrFile = new File(filePath);
        BufferedImage bufferedImage = ImageIO.read(qrFile);

        // Convert image to binary bitmap source
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        // Decode the QR code
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText(); // Extract text from QR
    }
}
