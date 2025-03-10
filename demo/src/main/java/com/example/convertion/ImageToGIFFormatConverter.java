
package com.example.convertion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageToGIFFormatConverter {

    // Convert  PNG/JPG/WEBP/BMP to gif
    public static void convertToGif(String inputImagePath, String outputPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        File outputFile = new File(outputPath);
        ImageIO.write(image, "gif", outputFile);
        System.out.println("Image successfully converted to GIF: " + outputPath);
    }
}