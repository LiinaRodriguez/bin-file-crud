package com.liinarodriguez.utils;

import com.liinarodriguez.entity.File;

import java.io.IOException;
import java.util.Arrays;
import java.io.FileInputStream;

public class FileProcessor {
    public File processFile(String path){
        File file = new File();
        try{
            byte [] binaryData = readFileToByteArray(path);
            file.setName(path);
            file.setBinary(binaryData);
            System.out.println("Processing file " + file.getName());
            System.out.println("bin"+ Arrays.toString(file.getBinary()));
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
