package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Pixel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

public class JsonIO {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void exportToJson(Pixel[] pixels, String path) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            gson.toJson(pixels, fw);
        }
    }
    public static Pixel[] importFromJson(String path) throws IOException {
        try (FileReader fr = new FileReader(path)) {
            return gson.fromJson(fr, Pixel[].class);
        }
    }
}