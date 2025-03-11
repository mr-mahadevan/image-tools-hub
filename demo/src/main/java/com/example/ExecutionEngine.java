package com.example;

import com.drew.imaging.ImageProcessingException;
import com.example.extract.MetadataExtractor;

import java.io.IOException;


public class ExecutionEngine extends Thread{

    @Override
    public void run() {

        String inputFile = "C:/Users/mahad/Pictures/image format testing folder/t1/";
        String outFile = "C:/Users/mahad/Pictures/image format testing folder/t1/";

        String jpg = inputFile+ "it.jpg";
        String png = inputFile+ "oi.png";
        String webp = inputFile+ "ew.webp";
        String bmp = inputFile+ "poi.bmp";
        String gif = inputFile+ "ger.gif";


        try {
            MetadataExtractor.extractData(jpg, outFile + "data.txt")
                    .entrySet()
                    .stream()
                    .forEach(map-> System.out.println(map.getKey() + " : " + map.getValue()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ImageProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
