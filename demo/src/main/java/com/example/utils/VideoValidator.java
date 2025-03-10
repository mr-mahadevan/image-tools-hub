package com.example.utils;


import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

// Video validation class
public class VideoValidator {
    private static final long MAX_SIZE_MB = 10;
    private static final double MAX_DURATION_SEC = 10;
    private final FFprobe ffprobe;
    private static final String FFPROBE_PATH = "C:/Users/mahad/Pictures/ffmpeg-master-latest-win64-gpl-shared/bin/ffprobe.exe";

    public VideoValidator() throws IOException {
        this.ffprobe = new FFprobe(FFPROBE_PATH);
    }

    public boolean isValid(String filePath) throws IOException {
        File file = new File(filePath);

        if (!filePath.toLowerCase().endsWith(".mp4")) {
            System.out.println("Invalid file format. Only MP4 allowed.");
            return false;
        }

        if (Files.size(file.toPath()) > MAX_SIZE_MB * 1024 * 1024) {
            System.out.println("File size exceeds 10MB.");
            return false;
        }

        double duration = getVideoDuration(filePath);
        if (duration > MAX_DURATION_SEC) {
            System.out.println("Video duration exceeds 10 seconds.");
            return false;
        }

        return true;
    }

    private double getVideoDuration(String filePath) throws IOException {
        FFmpegProbeResult probeResult = ffprobe.probe(filePath);
        return probeResult.getFormat().duration;
    }
}

