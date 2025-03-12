package com.example.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageUtils {

    public static Map<String, String> getImageSize(String imagePath) {
        File file = new File(imagePath);
        if (!file.exists()) {
            return Map.of("Error", "File does not exist: " + imagePath);
        }

        String format = getFileExtension(file);
        long fileSize = file.length();
        BufferedImage image = null;

        try {
            // Read Image (WEBP requires TwelveMonkeys)
            image = ImageIO.read(file);

            if (image == null) {
                return Map.of("Error", "Unsupported or corrupted image format: " + imagePath);
            }

            int width = image.getWidth();
            int height = image.getHeight();

            // Create a mutable HashMap and store values
            Map<String, String> sizeData = new HashMap<>();
            sizeData.put("Image", file.getName());
            sizeData.put("Format", format.toUpperCase());
            sizeData.put("Width", String.valueOf(width));
            sizeData.put("Height", String.valueOf(height));
            sizeData.put("Size", String.format("%.2f KB", fileSize / 1024.0));

            return sizeData;

        } catch (IOException e) {
            e.printStackTrace();
            return Map.of("Error", "Error reading image: " + e.getMessage());
        }
    }

    /**
     * Extracts the file extension from a given file.
     *
     * @param file The file to check.
     * @return The file extension.
     */
    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf('.');
        return (lastIndex == -1) ? "" : name.substring(lastIndex + 1);
    }

}
