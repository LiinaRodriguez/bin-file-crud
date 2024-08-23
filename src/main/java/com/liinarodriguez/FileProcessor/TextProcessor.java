package com.liinarodriguez.FileProcessor;


import java.io.IOException;
import java.util.Arrays;
import java.io.FileInputStream;
import com.liinarodriguez.entity.File;

public class TextProcessor implements FileProcessor{
    @Override
    public File processFile(String path) {
        System.out.println("Processing file: " + path);
        File file = null;
        try {
            byte[] binaryData = readFileToByteArray(path);
            file = new File.FileBuilder()
                    .setName(path)
                    .setBinary(binaryData)
                    .setType("txt")
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
