
package com.example.convertion;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageToGIFConverter {

    private static final String FFMGPEG_PATH = "C:/Users/mahad/Pictures/ffmpeg-master-latest-win64-gpl-shared/bin/ffmpeg.exe";


    // Convert  PNG/JPG/WEBP/BMP to gif
    public static void convertToGif(String inputImagePath, String outputPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        File outputFile = new File(outputPath);
        ImageIO.write(image, "gif", outputFile);
        System.out.println("Image successfully converted to GIF: " + outputPath);
    }

    public static void convertMp4ToGif(String inputFile, String outputFile, int frameRate) throws IOException {
        FFmpeg ffmpeg = new FFmpeg(FFMGPEG_PATH);

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(inputFile)       // Input MP4 file
                .overrideOutputFiles(true) // Overwrite existing file
                .addOutput(outputFile)     // Output file (GIF)
                .setFormat("gif")          // Convert to GIF
                .setVideoFrameRate(frameRate) // Set FPS for GIF smoothness
                .done();

        ffmpeg.run(builder);
        System.out.println("GIF conversion successful: " + outputFile);
    }
}