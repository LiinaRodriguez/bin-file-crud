package com.liinarodriguez.FileProcessor;

import com.liinarodriguez.entity.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ImageProcessor implements FileProcessor{
    @Override
    public File processFile(String path) {
        System.out.println("Processing image " + path);
        File file = null;
        try {
            byte[] binaryData = readFileToByteArray(path);
            file = new File.FileBuilder()
                    .setName(path)
                    .setBinary(binaryData)
                    .setType("img")
                    .build();
            System.out.println("Processing file " + file.getName());
            System.out.println("bin" + Arrays.toString(file.getBinary()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while reading file");
        }

        return file;
    }
    public byte[] readFileToByteArray(String path) throws IOException {
        try (FileInputStream fis = new FileInputStream(path)) {
            return fis.readAllBytes();
        }
    }
}
