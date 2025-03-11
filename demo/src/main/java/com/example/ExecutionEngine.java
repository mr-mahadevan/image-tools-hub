package com.example;

import com.example.convertion.ImageToPDFConverterService;
import com.example.edit.ImageCrop;
import com.example.edit.ImageFlipper;
import com.example.edit.ImageRotate;
import com.example.edit.enums.AspectRatio;
import com.example.secure.WatermarkUtility;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<String> files = new ArrayList(Arrays.asList(jpg,png,bmp,gif));
        List<String> files2 = new ArrayList(Arrays.asList(jpg));
        System.out.println(jpg);


        String data = "I wanna hug you tonight";
        String fileName = "barcode.png";
        int width = 600;
        int height = 200;
        int foregroundColor = 000; // Blue
        int backgroundColor = 0xFFFFFFFF; // White

        int[] angles = {90};

        int targetWidth = 1080;           // Example: Instagram post (4:5)
        int targetHeight = 1350;

        ImageCrop.cropAndResizeImage(jpg, outFile + "cropped.jpg", AspectRatio.INSTAGRAM_PORTRAIT);
    }
}
