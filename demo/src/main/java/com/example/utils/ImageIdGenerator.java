package com.example.utils;

public class ImageIdGenerator {

    public static String idGenerator() {
        return java.util.UUID.randomUUID().toString();
    }
}