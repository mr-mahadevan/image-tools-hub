package com.example;

import com.example.edit.ImageFlipper;
import com.example.secure.WatermarkUtility;

import java.awt.*;
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
            ImageFlipper.flipVertical(inputFile + "sty.jpg", outFile + "hr.jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Flipping Completed");


    }
}
