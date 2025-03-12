package com.example;

import com.example.compress.ImageCompressor;
import com.example.utils.ImageUtils;

import java.util.Map;


public class ExecutionEngine extends Thread {

    @Override
    public void run() {

        String inputFile = "C:/Users/mahad/Pictures/image format testing folder/t1/";
        String outFile = "C:/Users/mahad/Pictures/image format testing folder/t1/";

        String jpg = inputFile + "op.jpg";
        String png = inputFile + "oi.png";
        String webp = inputFile + "ew.webp";
        String bmp = inputFile + "poi.bmp";
        String gif = inputFile + "ger.gif";
    }
}
