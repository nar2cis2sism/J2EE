package app.servlet;

import app.parser.GetUserInfoParser;
import app.parser.LoginParser;
import app.parser.NavigationParser;
import app.servlet.util.EntityUtil;
import app.servlet.util.GsonUtil;
import app.servlet.util.RequestDispatcher;
import app.socket.SocketManager;
import engine.java.log.LogFactory;
import engine.java.log.LogFactory.LOG;
import engine.java.util.CalendarFormat;
import engine.java.util.io.IOUtil;
import engine.java.util.string.TextUtils;

import org.json.JSONObject;

import protocol.java.json.ErrorInfo;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppServlet extends HttpServlet {
    
    public static final String SERVER_IP        = "127.0.0.1";
    private static final int SERVER_PORT         = 8080;
    
    public static final String SERVER_URL = String.format("http://%s:%d/AppServer/", SERVER_IP, SERVER_PORT);
    public static final String APP_URL = SERVER_URL + "app";
    
    public static final boolean isTest = true;
	
    private static final long serialVersionUID = 1L;

    @Override
	public void init() throws ServletException {
		initLog();
		initParser();
		initSocket();
	}
	
	private void initLog() {
		LogFactory.init(new File(getServletContext().getRealPath("logs"), 
				CalendarFormat.format(Calendar.getInstance(), "yyyyMMdd_HHmmss")));
//		LogFactory.enableLOG(true);
	}
	
	private void initParser() {
	    RequestDispatcher.register("navigation", NavigationParser.class);
	    RequestDispatcher.register("login", LoginParser.class);
	    RequestDispatcher.register("get_user_info", GetUserInfoParser.class);
	}
	
	private void initSocket() {
	    SocketManager.setup();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	    LOG.log(String.format("收到来自%s的请求:", req.getRemoteAddr()));
	    
	    String response;
	    try {
            String request = EntityUtil.toString(IOUtil.readStream(req.getInputStream()));
            LOG.log(request);
            
            JSONObject json = new JSONObject(request);
            String action = json.getString("action");
            if (TextUtils.isEmpty(action))
            {
                throw new NullPointerException("action=" + action);
            }
            
            response = RequestDispatcher.dispatch(action, json);
        } catch (Exception e) {
            LOG.log(e);
            response = GsonUtil.toJson(new ErrorInfo(400));
        }
	    
	    LOG.log(String.format("响应来自%s的请求:", req.getRemoteAddr()));
        LOG.log(response);
        
	    resp.setContentType("text/json;charset=UTF-8");
	    resp.getOutputStream().write(EntityUtil.toByteArray(response));
	    resp.getOutputStream().flush();
	}
}