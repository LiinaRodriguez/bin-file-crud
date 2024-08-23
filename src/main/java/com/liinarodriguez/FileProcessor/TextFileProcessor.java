package com.liinarodriguez.FileProcessor;

public class TextFileProcessor extends FileProcessorFactory {
    @Override
    public FileProcessor createFileProcessor() {
        return new TextProcessor();
    }
}
