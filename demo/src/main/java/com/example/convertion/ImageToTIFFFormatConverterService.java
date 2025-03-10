package com.example.convertion;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToTIFFFormatConverterService {

    // Using Apache Commons-Imaging Library
    // Supported format JPG/PNG/BMP/WEBP/GIF to TIFF
    public static void toConvert(String inputImagePath, String outputImagePath) throws IOException, ImageWriteException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        if (image == null) {
            throw new IOException("Invalid image format or file not found.");
        }

        Imaging.writeImage(image, new File(outputImagePath), ImageFormats.TIFF);
        System.out.println("[✅ Image successfully converted to TIFF] :" + outputImagePath);
    }
}
