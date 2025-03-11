package com.example.convertion;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageToBMPConverter {

    // Convert JPG/WEBP/GIF/PNG to BMP
    public static void convertToBmp(String inputImagePath, String outputPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        File outputFile = new File(outputPath);
        ImageIO.write(image, "bmp", outputFile);
        System.out.println("Image successfully converted to BMP: " + outputPath);
    }
}