package com.example;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class JpgAndPngToOtherFormatConverter {

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


    // Convert  PNG/JPG/BMP/GIF to webp
    public static void convertToWebP(String inputImagePath , String outputPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        // Ensure WebP format is available
        if (ImageIO.getImageWritersByFormatName("webp").hasNext()) {
            ImageIO.write(image, "webp", new File(outputPath));
            System.out.println("Image successfully converted to WebP: " + outputPath);
        } else {
            System.err.println("WebP format not supported. Ensure Sejda WebP library is included.");
        }
    }

    // Convert  PNG/JPG/WEBP/BMP to gif
    public static void convertToGif(String inputImagePath, String outputPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        File outputFile = new File(outputPath);
        ImageIO.write(image, "gif", outputFile);
        System.out.println("Image successfully converted to GIF: " + outputPath);
    }

    // Convert PNG/JPG/WEBP/BMP to BMP
    public static void convertToBmp(String inputImagePath, String outputPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        File outputFile = new File(outputPath);
        ImageIO.write(image, "bmp", outputFile);
        System.out.println("Image successfully converted to BMP: " + outputPath);
    }

}
