package com.example;

import java.io.IOException;
import java.util.*;

import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.B2DownloadAuthorization;
import com.example.convertion.ImageToPNGConverterService;

import bucket.B2Utils;
import bucket.StoreImage;

public class PNGtoOtherFormat extends Thread{
    
    public void run(){

        String webp = "ew.webp";
        String gif = "ger.gif";
        String bmp = "poi.bmp";
        String jpg = "it.jpg";
        String png = "at.png";

        String inputFile = "C:/Users/mahad/Pictures/image format testing folder/t1/" + gif;
        String outputFile = "C:/Users/mahad/Pictures/image format testing folder/t1/" + bmp + "+" + UUID.randomUUID().toString();

     /*   StoreImage storeImage = new StoreImage();
        try {
            storeImage.storeImage(inputFile, ".bmp");
        } catch (B2Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  */

        try{
            String url = B2Utils.contructPreSignedURL(webp);
            System.out.println("Image URL: " + url);
        }catch(B2Exception e){
            e.printStackTrace();
            System.out.println("Error downloading image");
        }
        

        try{
          ImageToPNGConverterService.convertToPNG(inputFile, outputFile + ".png");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error converting image");
        }
    }
}
