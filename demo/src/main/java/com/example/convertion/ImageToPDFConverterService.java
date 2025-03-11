package com.example.convertion;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ImageToPDFConverterService {

    // Convert PNG/JPG/WEBP/BMP/GIF to PDF
    public static void convertToPdf(List<String> inputImagePaths, String outputPath) throws IOException {
        File outputFile = new File(outputPath);

        // conditions
        if (calculateTotalSize(inputImagePaths) < 25 || getTotalImageFiles(inputImagePaths) < 20) {
            try (PDDocument document = new PDDocument()) {
                for (String inputImagePath : inputImagePaths) {
                    BufferedImage image = ImageIO.read(new File(inputImagePath));
                    if (image != null) {
                        addImageToDocument(document, image);
                    }
                }
                document.save(outputFile);
            }
            System.out.println("Images successfully converted to PDF: " + outputPath);
        }
        else
            System.out.println("Please total upload size < 25 MB size & maximum 20 images limited");

    }

    private static void addImageToDocument(PDDocument document, BufferedImage image) throws IOException {
        float imageWidth = image.getWidth();
        float imageHeight = image.getHeight();
        PDPage page = new PDPage(new PDRectangle(imageWidth, imageHeight));
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
            contentStream.drawImage(pdImage, 0, 0, imageWidth, imageHeight);
        }
    }

    private static long calculateTotalSize(List<String> imagePaths) {
        long totalSizeBytes = 0;
        for (String path : imagePaths) {
            File file = new File(path);
            if (file.exists()) {
                totalSizeBytes += file.length();
            }
        }

        double totalSizeKB = totalSizeBytes / 1024.0;
        double totalSizeMB = totalSizeKB / 1024.0;

        System.out.println("Total");

        if (totalSizeMB >= 1) {
            return (long) totalSizeMB;
        } else {
            return (long) totalSizeKB;
        }
    }

    private static int getTotalImageFiles(List<String> imagePaths) {
        int count = 0;
        for (String path : imagePaths) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                count++;
            }
        }
        System.out.println("Total Images: " + count);
        return count;
    }



}
