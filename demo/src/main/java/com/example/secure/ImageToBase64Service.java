package com.example.secure;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class ImageToBase64Service {

    // Supported format JPG/
    public static String encodeImageToBase64(String imagePath) throws IOException {
        File inputFile = new File(imagePath);
        BufferedImage image = ImageIO.read(inputFile);

        // Resize to reduce file size (optional)
        int newWidth = image.getWidth(); // Set your preferred width
        int newHeight = (int) (image.getHeight() * ((double) newWidth / image.getWidth()));
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, newWidth, newHeight, null);
        g.dispose();

        // Convert resized image to ByteArray
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", outputStream); // Use "png" for PNG format
        byte[] imageBytes = outputStream.toByteArray();

        // Encode to Base64
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static void decodeBase64ToImage(String base64Image, String outputImagePath) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputImagePath));
        fileOutputStream.write(imageBytes);
        fileOutputStream.close();
    }
}
