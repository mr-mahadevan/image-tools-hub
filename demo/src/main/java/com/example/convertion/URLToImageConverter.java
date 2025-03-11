package com.example.convertion;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Content;


public class URLToImageConverter {

    private static final String ABSTRACT_API_KEY = "ad60d81d3d884af5b62206fdcb72195c";

    public static void makeImage(String url, String outputPath){

        try {

            Content content =  Request
                    .Get("https://screenshot.abstractapi.com/v1/?api_key=" + ABSTRACT_API_KEY +
                            "&url=" + url)
                    .execute()
                    .returnContent();

            byte[] imageByte = content.asBytes();
                try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                    fos.write(imageByte);
                }
        }
        catch (IOException error) { System.out.println(error); }
    }
    
}
