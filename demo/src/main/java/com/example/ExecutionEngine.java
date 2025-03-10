package com.example;

import com.example.scan.ColorExtractorService;
import com.example.scan.QRCodeGeneratorService;
import com.example.secure.ImageSteganography;
import com.example.utils.ImageIdGenerator;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

import java.awt.*;
import java.io.IOException;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            ImageSteganography.extractData(outFile + "Hided.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Generation Completed");
    }
}
