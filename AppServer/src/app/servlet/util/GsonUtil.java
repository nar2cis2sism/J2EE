package app.servlet.util;

import com.google.gson.Gson;

public class GsonUtil {
    
    private static final ThreadLocal<Gson> gson = new ThreadLocal<Gson>() {
        
        protected Gson initialValue() {
            return new Gson();
        };
    };
    
    public static String toJson(Object src) {
        return gson.get().toJson(src);
    }
}