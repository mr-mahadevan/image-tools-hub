package com.example.convertion;

import net.sf.image4j.codec.ico.ICOEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToIconFormatConverterService {

    private static final int ICON_SIZE = 250;

    // Supported format PNG/JPG/BMP/GIF/WEBP to ICON
    public static void toConvert(String inputImagePath, String outputImagePath) throws IOException {
        // Load the input image
        BufferedImage originalImage = ImageIO.read(new File(inputImagePath));

        // Process the image (crop & resize)
        BufferedImage processedImage = resizeAndCropImage(originalImage);

        // Save the final image as ICO
        ICOEncoder.write(processedImage, new File(outputImagePath));

        System.out.println("✅ Image successfully cropped and converted to ICO: " + outputImagePath);
    }

    private static BufferedImage resizeAndCropImage(BufferedImage image) {
        int cropSize = Math.min(image.getWidth(), image.getHeight());
        int cropX = (image.getWidth() - cropSize) / 2;
        int cropY = (image.getHeight() - cropSize) / 2;

        // Crop the image (centered square)
        BufferedImage croppedImage = image.getSubimage(cropX, cropY, cropSize, cropSize);

        // Resize to target size
        BufferedImage resizedImage = new BufferedImage(ImageToIconFormatConverterService.ICON_SIZE, ImageToIconFormatConverterService.ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(croppedImage.getScaledInstance(ImageToIconFormatConverterService.ICON_SIZE, ImageToIconFormatConverterService.ICON_SIZE, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }
}
