package com.example.secure;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.Map;

public class ImageToBase64Service {

    // Supported format JPG/PNG/WEBP/BMP/GIF to Base64
    public static Map<String,String> encodeImageToBase64(String imagePath, String filePath) throws IOException {
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

        String base64String = Base64.getEncoder().encodeToString(imageBytes);

        // Save file as text format
        saveBase64ToFile(base64String, filePath);


        // Encode to Base64
        return Map.of("Base64String",base64String,"filePath", filePath);
    }

    // Supported format JPG/PNG/WEBP/BMP/GIF Base64-String to JPG Image
    public static void decodeBase64ToImage(String base64Image, String base64FilePath, String outputImagePath) throws IOException {
        // Try to read Base64 from the file first
        String base64String = readBase64FromFile(base64FilePath);

        // If the file is missing or unreadable, use the provided Base64 string
        if (base64String == null || base64String.isEmpty()) {
            base64String = base64Image;
        }

        // Decode Base64 and save as image
        byte[] imageBytes = Base64.getDecoder().decode(base64String);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputImagePath));
        fileOutputStream.write(imageBytes);
        fileOutputStream.close();
        System.out.println("Image successfully saved as: " + outputImagePath);
    }


    private static void saveBase64ToFile(String base64String, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(base64String);
        writer.close();
        System.out.println("Successfully saved Base64 file: " + filePath);
    }

    private static String readBase64FromFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder base64String = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                base64String.append(line);
            }
            reader.close();
            return base64String.toString();
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Using provided Base64 string instead.");
            return null; // File not found, return null, so we can handle it
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
