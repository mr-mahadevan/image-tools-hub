package com.example.edit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRotate {

    // Rotate image by a given angle
    private static BufferedImage rotateImage(BufferedImage image, double angle) {
        int w = image.getWidth();
        int h = image.getHeight();
        double radians = Math.toRadians(angle);

        // Calculate new dimensions after rotation
        int newW = (int) Math.round(Math.abs(w * Math.cos(radians)) + Math.abs(h * Math.sin(radians)));
        int newH = (int) Math.round(Math.abs(h * Math.cos(radians)) + Math.abs(w * Math.sin(radians)));

        // Create rotated image
        BufferedImage rotatedImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Set quality settings
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Apply transformation
        AffineTransform transform = new AffineTransform();
        transform.translate((newW - w) / 2.0, (newH - h) / 2.0);
        transform.rotate(radians, w / 2.0, h / 2.0);
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }

    public static void processRotations(String inputPath, String outputDir, int[] angles) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(inputPath));

            for (int angle : angles) {
                BufferedImage rotatedImage = rotateImage(originalImage, angle);
                ImageIO.write(rotatedImage, "png", new File(outputDir));
            }
            System.out.println("Image rotation completed successfully.");
        } catch (Exception e) {
            System.err.println("Error processing image: " + e.getMessage());
        }
    }

}
