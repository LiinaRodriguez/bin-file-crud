package com.liinarodriguez.FileProcessor;


import com.liinarodriguez.entity.File;

import java.io.IOException;


public interface FileProcessor {
    public File processFile(String path);
    public byte[] readFileToByteArray(String path) throws IOException;

}
