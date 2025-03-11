package com.example.edit.enums;

public enum AspectRatio {

    SQUARE(1080, 1080),            // 1:1 (Instagram, LinkedIn)
    INSTAGRAM_PORTRAIT(1080, 1350), // 4:5 (Instagram Post)
    LANDSCAPE(1920, 1080),         // 16:9 (YouTube, Facebook Cover)
    STORY(1080, 1920),             // 9:16 (Instagram Stories, TikTok)
    PHOTOGRAPHY(1800, 1200),       // 3:2 (Photography)
    PORTRAIT(1200, 1800);          // 2:3 (Portrait Photography)

    private final int width;
    private final int height;

    AspectRatio(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getAspectRatio() {
        return (float) width / height;
    }


}
