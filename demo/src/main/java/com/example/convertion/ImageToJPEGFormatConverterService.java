package com.example.convertion;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageToJPEGFormatConverterService {

    // Converting PNG/WEBP/GIF/BMP to jpeg
    public static void convertToJpeg(String inputImagePath, String outputPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        File outputFile = new File(outputPath);
    
        // Convert the image to TYPE_INT_RGB if necessary
        if (image.getType() != BufferedImage.TYPE_INT_RGB) {
            BufferedImage rgbImage = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = rgbImage.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            image = rgbImage;
        }
    
        // Write the image as JPEG
        boolean result = ImageIO.write(image, "jpg", outputFile);
        if (result) {
            System.out.println("Image successfully converted to JPEG: " + outputPath);
        } else {
            throw new IOException("No JPEG writer found.");
        }
    }

}
