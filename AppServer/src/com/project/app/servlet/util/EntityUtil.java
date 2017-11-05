package com.project.app.servlet.util;

import java.io.UnsupportedEncodingException;

import engine.java.util.secure.Blowfish;
import engine.java.util.string.TextUtils;

public class EntityUtil {
    
    private static final boolean encrypt = true;
    
    private static final Blowfish encryptor;
    
    static
    {
        if (encrypt)
        {
            encryptor = new Blowfish();
            encryptor.setKey("I'm super man".getBytes());
        }
    }
    
    public static String toString(byte[] data) throws UnsupportedEncodingException {
        if (data == null) return "";
        if (encryptor != null) data = encryptor.decrypt(data);
        return new String(data, "UTF-8");
    }
    
    public static byte[] toByteArray(String entity) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(entity)) return new byte[0];
        
        byte[] data = entity.getBytes("UTF-8");
        if (encryptor != null) data = encryptor.encrypt(data);
        return data;
    }
}