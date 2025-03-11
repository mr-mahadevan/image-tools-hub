package com.example.secure;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WatermarkUtility {

    public static void addWatermark(String inputImage, String outputImage,
                                    String watermark, String position){
        try {
            // Load the original image
            File sourceImageFile = new File(inputImage);
            BufferedImage originalImage = ImageIO.read(sourceImageFile);

            // Create a graphics context
            Graphics2D g2d = (Graphics2D) originalImage.getGraphics();

            // Set the font and watermark properties
            Font font = new Font("Segoe Script", Font.BOLD | Font.ITALIC, 70);
            g2d.setFont(font);
            g2d.setColor(new Color(28, 253, 1, 255)); // Red color with transparency

            // Enable anti-aliasing for smooth text
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Watermark text
           // String watermarkText = "Watermark";

            // Get image dimensions
            int imageWidth = originalImage.getWidth();
            int imageHeight = originalImage.getHeight();
            int textWidth = g2d.getFontMetrics().stringWidth(watermark);
            int textHeight = g2d.getFontMetrics().getHeight();

            // Set the position (Change this for different locations)
          //  String position = "bottom-right";  // Change this to: top, center, bottom, top-right, etc.
            int x = 0, y = 0;
            int padding = 35; // Adjust padding to increase space from edges

            switch (position.toLowerCase()) {
                case "top-left":
                    x = padding;
                    y = textHeight + padding;
                    break;
                case "top-right":
                    x = imageWidth - textWidth - padding;
                    y = textHeight + padding;
                    break;
                case "top":
                    x = (imageWidth - textWidth) / 2;
                    y = textHeight + padding;
                    break;
                case "center":
                    x = (imageWidth - textWidth) / 2;
                    y = (imageHeight + textHeight) / 2;
                    break;
                case "bottom-left":
                    x = padding;
                    y = imageHeight - padding;
                    break;
                case "bottom-right":
                    x = imageWidth - textWidth - padding;
                    y = imageHeight - padding;
                    break;
                case "bottom":
                    x = (imageWidth - textWidth) / 2;
                    y = imageHeight - padding;
                    break;
                case "center-left":
                    x = padding;
                    y = (imageHeight + textHeight) / 2;
                    break;
                case "center-right":
                    x = imageWidth - textWidth - padding;
                    y = (imageHeight + textHeight) / 2;
                    break;
                default:
                    System.out.println("Invalid position, using bottom-right as default.");
                    x = imageWidth - textWidth - padding;
                    y = imageHeight - padding;
            }

            // Add the watermark text to the image
            g2d.drawString(watermark, x, y);

            // Dispose of graphics context
            g2d.dispose();

            // Save the watermarked image
            File outputImageFile = new File(outputImage);
            ImageIO.write(originalImage, "jpg", outputImageFile);

            System.out.println("Watermark added at " + position + " successfully!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
