package com.example.compress;

import ar.com.hjg.pngj.FilterType;
import ar.com.hjg.pngj.PngReader;
import ar.com.hjg.pngj.PngWriter;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageCompressor {

    public static void CompressJPG(String inputImageFile, String outputFile, float quality){

        try {
            File inputFile = new File(inputImageFile);
            BufferedImage image = ImageIO.read(inputFile);

            // Output File
            File compressedFile = new File(outputFile);
            FileOutputStream fos = new FileOutputStream(compressedFile);

            // Get Image Writer for JPG format
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
            if (!writers.hasNext()) {
                throw new IllegalStateException("No writers found for JPG format.");
            }
            ImageWriter writer = writers.next();

            // Set Output Stream
            ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
            writer.setOutput(ios);

            // Set Compression Quality
            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality); // Set quality (0.0 to 1.0, where 1.0 is max quality)

            // Write the image
            writer.write(null, new IIOImage(image, null, null), param);

            // Close streams
            ios.close();
            fos.close();
            writer.dispose();

            System.out.println("Image compression completed. Compressed file: " + compressedFile.getAbsolutePath());
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
