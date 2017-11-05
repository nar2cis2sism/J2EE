package com.project.server.script.http;

import java.io.IOException;
import java.io.InputStream;

import engine.java.util.io.IOUtil;

public class FileContent {
    
	/**
	 * 读取文件内容
	 * 
	 * @param fileName 文件名
	 */
    public static byte[] read(String fileName) throws IOException {
        InputStream is = null;
        try {
            is = FileContent.class.getResourceAsStream(fileName);
            if (is == null)
            {
                throw new IOException("No resource:" + fileName);
            }
            
            return IOUtil.readStream(is);
        } finally {
            if (is != null)
                is.close();
        }
    }
}