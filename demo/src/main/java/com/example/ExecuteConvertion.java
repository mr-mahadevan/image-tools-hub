package com.example;

import java.io.IOException;
import java.util.*;

import com.example.convertion.ImageToBMPFormatConverterService;
import com.example.convertion.ImageToGIFFormatConverter;
import com.example.convertion.ImageToWebPConverterService;

public class ExecuteConvertion extends Thread{
    
    @Override
    public void run(){

        String webp = "ew.webp";
        String gif = "ger.gif";
        String bmp = "poi.bmp";
        String jpg = "it.jpg";
        String png = "at.png";


        String toBMP = ".bmp";

        String inputFile = "C:/Users/mahad/Pictures/image format testing folder/t1/";
        String outputFile = "C:/Users/mahad/Pictures/image format testing folder/t1/" + UUID.randomUUID().toString();

        
        try {
            System.out.println("Starting time: " + (System.currentTimeMillis()/ 24 * 24));
            ImageToBMPFormatConverterService.convertToBmp(inputFile + jpg, outputFile + "1" + toBMP);
            ImageToGIFFormatConverter.convertToGif(inputFile + webp, outputFile + "2" + toBMP);
            ImageToGIFFormatConverter.convertToGif(inputFile + gif, outputFile + "3" + toBMP);
            ImageToGIFFormatConverter.convertToGif(inputFile + png, outputFile + "4" + toBMP);
            System.out.println("Starting time: " + System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
