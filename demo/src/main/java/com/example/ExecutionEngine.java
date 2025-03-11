package com.example;

import com.example.edit.ImageCrop;
import com.example.edit.enums.AspectRatio;

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
    }
}
