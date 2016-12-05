package app.servlet.util;

import java.io.UnsupportedEncodingException;

import engine.java.util.secure.Blowfish;

public class EntityUtil {
    
    private static final boolean encrypt = true;
    
    private static Blowfish encryptor;
    
    static
    {
        if (encrypt)
        {
            encryptor = new Blowfish();
            encryptor.setKey("I'm super man".getBytes());
        }
    }
    
    public static String toString(byte[] data) throws UnsupportedEncodingException {
        if (encryptor != null)
            data = encryptor.decrypt(data);
        
        return new String(data, "UTF-8");
    }
    
    public static byte[] toByteArray(String entity) throws UnsupportedEncodingException {
        byte[] data = entity.getBytes("UTF-8");
        if (encryptor != null)
            data = encryptor.encrypt(data);
        
        return data;
    }
}