package com.example.convertion;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageToWebPConverter {

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
    
}
