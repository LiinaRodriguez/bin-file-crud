package com.liinarodriguez.FileProcessor;

public class ImageFileProcessor extends FileProcessorFactory{
    @Override
    public FileProcessor createFileProcessor() {
        return new ImageProcessor();
    }
}
