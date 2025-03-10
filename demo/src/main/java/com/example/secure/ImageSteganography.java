package com.example.secure;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSteganography {

    public static void hideMessage(String inputFile, String outputFile, String data) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputFile));
        int width = image.getWidth();
        int height = image.getHeight();
        int msgIndex = 0;

        // Convert message to binary
        String binaryMessage = toBinary(data) + "1111111111111110"; // End marker

        for (int y = 0; y < height && msgIndex < binaryMessage.length(); y++) {
            for (int x = 0; x < width && msgIndex < binaryMessage.length(); x++) {
                int pixel = image.getRGB(x, y);

                int blue = pixel & 0xFF;  // Extract the blue component
                blue = (blue & 0xFE) | (binaryMessage.charAt(msgIndex) - '0'); // Modify LSB
                msgIndex++;

                int newPixel = (pixel & 0xFFFFFF00) | blue;
                image.setRGB(x, y, newPixel);
            }
        }

        ImageIO.write(image, "png", new File(outputFile));
        System.out.println("Message hidden successfully!");
    }

    private static String toBinary(String message) {
        StringBuilder binary = new StringBuilder();
        for (char c : message.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }
        return binary.toString();
    }

    public static void extractData(String inputFile) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputFile));
        int width = image.getWidth();
        int height = image.getHeight();
        StringBuilder binaryMessage = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int blue = pixel & 0xFF;
                binaryMessage.append(blue & 1); // Extract LSB

                if (binaryMessage.length() >= 16 && binaryMessage.substring(binaryMessage.length() - 16).equals("1111111111111110")) {
                    System.out.println("Message extracted: " + toText(binaryMessage.substring(0, binaryMessage.length() - 16)));
                    return;
                }
            }
        }
    }

    private static String toText(String binary) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteStr = binary.substring(i, i + 8);
            text.append((char) Integer.parseInt(byteStr, 2));
        }
        return text.toString();
    }


}
