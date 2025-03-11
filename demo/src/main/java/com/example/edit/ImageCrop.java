package com.example.edit;

import com.example.edit.enums.AspectRatio;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCrop {

    public static void cropAndResizeImage(String inputPath, String outputPath, AspectRatio aspectRatio) {
        try {
            BufferedImage inputImage = ImageIO.read(new File(inputPath));
            if (inputImage == null) {
                System.out.println("Invalid image file.");
                return;
            }

            float originalAspect = (float) inputImage.getWidth() / inputImage.getHeight();
            float targetAspect = aspectRatio.getAspectRatio();

            int cropWidth = inputImage.getWidth();
            int cropHeight = inputImage.getHeight();

            // Adjust crop dimensions to maintain target aspect ratio
            if (originalAspect > targetAspect) {
                cropWidth = (int) (cropHeight * targetAspect);
            } else {
                cropHeight = (int) (cropWidth / targetAspect);
            }

            int cropX = (inputImage.getWidth() - cropWidth) / 2;
            int cropY = (inputImage.getHeight() - cropHeight) / 2;

            // Crop the image
            BufferedImage croppedImage = Scalr.crop(inputImage, cropX, cropY, cropWidth, cropHeight);

            // Resize to target width and height from AspectRatio
            BufferedImage resizedImage = Scalr.resize(croppedImage, Scalr.Method.QUALITY, aspectRatio.getWidth(), aspectRatio.getHeight());

            // Save the output image
            ImageIO.write(resizedImage, "jpg", new File(outputPath));

            System.out.println("Image cropped and resized successfully: " + outputPath);

        } catch (IOException e) {
            System.err.println("Error processing image: " + e.getMessage());
        }
    }

}
