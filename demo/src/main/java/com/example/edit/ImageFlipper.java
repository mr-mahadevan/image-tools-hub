package com.example.edit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFlipper {



    // Flip vertically (up/down)
    public static void flipVertical(String inputPath, String outputPath) throws IOException {
        File inputFile = new File(inputPath); // Change the file path accordingly
        BufferedImage original = ImageIO.read(inputFile);
        int w = original.getWidth();
        int h = original.getHeight();
        BufferedImage flipped = new BufferedImage(w, h, original.getType());
        Graphics2D g = flipped.createGraphics();
        g.drawImage(original, 0, 0, w, h, 0, h, w, 0, null);
        g.dispose();

        // Save flipped images
        ImageIO.write(flipped, "jpg", new File(outputPath));
    }

    // Flip horizontally (left/right)
    public static void flipHorizontal(String inputPath, String outputPath) throws IOException {
        File inputFile = new File(inputPath); // Change the file path accordingly
        BufferedImage original = ImageIO.read(inputFile);
        int w = original.getWidth();
        int h = original.getHeight();
        BufferedImage flipped = new BufferedImage(w, h, original.getType());
        Graphics2D g = flipped.createGraphics();
        g.drawImage(original, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();

        // Save flipped images
        ImageIO.write(flipped, "jpg", new File(outputPath));
    }
}
