package com.example.convertion;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ImageToPDFConverterService {

    // Convert PNG/JPG/WEBP/BMP/GIF to PDF
    public static void convertToPdf(String inputImagePath, String outputPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        File outputFile = new File(outputPath);
        try (PDDocument document = new PDDocument()) {
            float imageWidth = image.getWidth();
            float imageHeight = image.getHeight();

            PDPage page = new PDPage(new PDRectangle(imageWidth, imageHeight));
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);

            contentStream.drawImage(pdImage, 0, 0, imageWidth, imageHeight);
            contentStream.close();

            document.save(outputFile);
        }
        System.out.println("Image successfully converted to PDF: " + outputPath);
    }



}
