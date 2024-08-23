package com.liinarodriguez.FileProcessor;

import com.liinarodriguez.entity.File;

public abstract class FileProcessorFactory {
    public abstract FileProcessor createFileProcessor();

    public File processFile(String filePath) {
        FileProcessor processor = createFileProcessor();
        return processor.processFile(filePath);
    }
}
