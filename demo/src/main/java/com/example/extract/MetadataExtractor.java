package com.example.extract;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MetadataExtractor {

    public static Map<String, String> extractData(String inputFile, String outputDataFile) throws IOException, ImageProcessingException {
        File imageFile = new File(inputFile);
        Metadata metadata = ImageMetadataReader.readMetadata(imageFile);

        HashMap<String, String> metadataMap = new HashMap<>();

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                metadataMap.put(tag.getTagName(), tag.getDescription());
                System.out.println(tag);
            }
        }

        // Save metadata to a text file
        saveMetadataToFile(metadataMap, outputDataFile);

        System.out.println("Metadata extracted and saved successfully.");
        return metadataMap;
    }

    private static void saveMetadataToFile(HashMap<String, String> metadataMap, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : metadataMap.entrySet()) {
                writer.write(entry.getKey() + " = " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
