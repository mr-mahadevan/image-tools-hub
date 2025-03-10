package com.example.scan;

import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.ml.clustering.*;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.util.Map;
import java.util.stream.Collectors;

public class ColorExtractorService {

    List<Map.Entry<String, Double>> sortedColors;

    public static void extractColors(String filePath, String outputFile) {
        try {
            File file = new File(filePath);
            // Load input image
            BufferedImage image = ImageIO.read(file); // Change image path

            int k = 5; // Number of dominant colors
            List<double[]> pixels = extractPixelColors(image, 5); // Extract colors with sampling

            // Perform K-Means clustering
            List<CentroidCluster<Clusterable>> clusters = new KMeansPlusPlusClusterer<>(k, 100, new EuclideanDistance())
                    .cluster(pixels.parallelStream().map(ClusterablePoint::new).collect(Collectors.toList()));

            // Count pixels in each cluster & calculate percentages
            int totalPixels = pixels.size();
            Map<String, Double> colorPercentages = clusters.stream()
                    .collect(Collectors.toMap(
                            cluster -> rgbToHex(cluster.getCenter().getPoint()),  // Key: HEX color
                            cluster -> (cluster.getPoints().size() * 100.0) / totalPixels, // Value: Percentage
                            Double::sum // Merge duplicate keys by summing percentages
                    ));

            // Sort colors by percentage (descending)
            List<Map.Entry<String, Double>> sortedColors = colorPercentages.entrySet().stream()
                    .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                    .collect(Collectors.toList());
            // Generate color palette image
            generateColorPaletteImage(sortedColors, outputFile);

            System.out.println("Color palette image saved as output.png!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        // Converts RGB to HEX
        private static String rgbToHex ( double[] rgb){
            return String.format("#%02x%02x%02x", (int) rgb[0], (int) rgb[1], (int) rgb[2]);
        }

        // Extracts pixel colors with downsampling
        private static List<double[]> extractPixelColors (BufferedImage image,int sampleRate){
            List<double[]> pixels = new ArrayList<>();
            for (int y = 0; y < image.getHeight(); y += sampleRate) {
                for (int x = 0; x < image.getWidth(); x += sampleRate) {
                    Color color = new Color(image.getRGB(x, y), true);
                    pixels.add(new double[]{color.getRed(), color.getGreen(), color.getBlue()});
                }
            }
            return pixels;
        }

        // Generates an image displaying extracted colors & percentages
        private static void generateColorPaletteImage(List<Map.Entry<String, Double>> colors, String outputPath) throws Exception {
            int width = 720, height = 500; // Horizontal layout
            BufferedImage paletteImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = paletteImage.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int startX = 0; // Start position for colors
            for (Map.Entry<String, Double> entry : colors) {
                int blockWidth = (int) (width * (entry.getValue() / 100.0));
                g2d.setColor(Color.decode(entry.getKey()));
                g2d.fillRect(startX, 0, blockWidth, height);

                // Rotate only the text
                AffineTransform originalTransform = g2d.getTransform();
                g2d.rotate(Math.toRadians(-90), startX + blockWidth / 2.0, height / 2.0);

                // Draw rotated text
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                g2d.drawString(entry.getKey() + " - " + String.format("%.2f%%", entry.getValue()), (float) (startX + blockWidth / 2.0), (float) (height / 2.0));

                // Restore original transformation
                g2d.setTransform(originalTransform);

                startX += blockWidth; // Move to next color block
            }

            g2d.dispose();
            ImageIO.write(paletteImage, "png", new File(outputPath));
            }

    }


