package com.example.convertion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageToPNGConverter {

    // Convert JPG/GIF/BMP/  to PNG
    public static void convertToPNG(String inputImagePath, String outputPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        File outputFile = new File(outputPath);
        ImageIO.write(image, "png", outputFile);
        System.out.println("Image successfully converted to PNG: " + outputPath);
    }

}
