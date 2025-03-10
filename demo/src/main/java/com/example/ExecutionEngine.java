package com.example;

import com.example.convertion.ImageToGIFFormatConverter;
import com.example.utils.VideoValidator;

import java.io.IOException;

public class ExecutionEngine extends Thread{

    @Override
    public void run() {

        String inputFile = "C:/Users/mahad/Pictures/image format testing folder/t1/";
        String outFile = "C:/Users/mahad/Pictures/image format testing folder/t1/";

        String jpg = "art.jpg";
        String png = "oi.png";
        String webp = "ew.webp";
        String bmp = "poi.bmp";
        String gif = "ger.gif";


        String data = "I wanna hug you tonight";
        String fileName = "barcode.png";
        int width = 600;
        int height = 200;
        int foregroundColor = 000; // Blue
        int backgroundColor = 0xFFFFFFFF; // White

        try {
            VideoValidator videoValidator = new VideoValidator();
            if (videoValidator.isValid(inputFile + "tiny.mp4")){
                ImageToGIFFormatConverter.convertMp4ToGif(inputFile  + "tiny.mp4",
                        outFile + "mp4.gif",
                        20);
            }else {
                System.out.println("Video does not meet the conditions.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Generation Completed");
    }
}
