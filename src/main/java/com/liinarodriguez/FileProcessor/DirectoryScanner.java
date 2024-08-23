package com.liinarodriguez.FileProcessor;

import com.liinarodriguez.entity.File;
import com.liinarodriguez.FileProcessor.FileProcessorFactory;
import java.util.ArrayList;
import java.util.List;

public class DirectoryScanner {
    File processedFile;
    public List<File> ScanDirectory(String path){
        java.io.File directory = new java.io.File(path);
        List<File> processedFiles = new ArrayList<File>();

        if(!directory.isDirectory()){
            throw new IllegalArgumentException("The given path is not a directory");
        }
        java.io.File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("The directory is empty.");
            return processedFiles;
        }
        for (java.io.File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                System.out.println("Scanning directory: " + fileName);
                if (fileName.endsWith(".txt")) {
                    processedFile = processFile(new TextFileProcessor(), file.getAbsolutePath());
                    processedFiles.add(processedFile);
                } else if (isImageFile(fileName)) {
                    processedFile = processFile(new ImageFileProcessor(), file.getAbsolutePath());
                    processedFiles.add(processedFile);
                }
            }
        }
        return processedFiles;
    }
    private File processFile(FileProcessorFactory factory, String filePath) {
        processedFile = factory.processFile(filePath);
        return processedFile;
    }
    private boolean isImageFile(String fileName) {
        String lowerCaseName = fileName.toLowerCase();
        return lowerCaseName.endsWith(".jpg") || lowerCaseName.endsWith(".jpeg") ||
                lowerCaseName.endsWith(".png") || lowerCaseName.endsWith(".bmp") ||
                lowerCaseName.endsWith(".gif");
    }
}
