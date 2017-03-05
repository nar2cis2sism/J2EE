package app.servlet.util;

import app.bean.User;
import engine.java.log.LogFactory.LOG;

import org.json.JSONObject;

import protocol.java.json.ErrorInfo;

import java.util.HashMap;

/**
 * 请求分发器
 */
public class RequestDispatcher {
    
    private static final HashMap<String, Class<? extends AppParser>> dispatcherMap
    = new HashMap<String, Class<? extends AppParser>>();
    
    /**
     * 需要提前注册请求处理器
     * 
     * @param action 指令名称
     * @param parserCls 指令解析类
     */
    public static void register(String action, Class<? extends AppParser> parserCls) {
        dispatcherMap.put(action, parserCls);
    }
    
    /**
     * 分发请求
     * 
     * @param action 指令名称
     * @param json 请求参数
     * @return 返回数据
     */
    public static String dispatch(String action, JSONObject json) {
        try {
            Class<? extends AppParser> cls = dispatcherMap.get(action);
            if (cls == null)
            {
                throw new NullPointerException("没有注册信令解析器:" + action);
            }
            
            AppParser parser = cls.newInstance();
            parser.parse(json);
            if (parser.response == null)
            {
                throw new NullPointerException("Call method setSuccess() or setFailure() to set response message.");
            }
            
            return parser.response;
        } catch (Exception e) {
            LOG.log(e);
        }
        
        return GsonUtil.toJson(new ErrorInfo(500));
    }
    
    public static abstract class AppParser {
        
        String response;
        
        public abstract void parse(JSONObject json) throws Exception;
        
        protected final void setSuccess(JSONObject data) {
            StringBuilder sb = new StringBuilder()
            .append("{\"code\":\"")
            .append(ErrorInfo.CODE_SUCCESS)
            .append("\"");
            if (data != null)
            {
                sb.append(",\"data\":").append(data.toString());
            }
            
            response = sb.append("}").toString();
        }
        
        protected final void setFailure(int code) {
            response = GsonUtil.toJson(new ErrorInfo(code));
        }
        
        /**
         * 请求格式错误
         */
        protected final void setRequestParamError() {
            setFailure(400);
        }
    }
    
    public abstract static class TokenParser extends AppParser {

        @Override
        public void parse(JSONObject json) throws Exception {
            String token = json.optString("token");
            if (token == null)
            {
                setRequestParamError();
                return;
            }
            
            User user = TokenManager.authenticate(token);
            if (user == null)
            {
                setFailure(407);
                return;
            }
            
            parse(json, user);
        }
        
        public abstract void parse(JSONObject json, User user) throws Exception;
    }
}