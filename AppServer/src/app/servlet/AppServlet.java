package app.servlet;

import app.network.http.parser.GetUserInfoParser;
import app.network.http.parser.LoginParser;
import app.network.http.parser.NavigationParser;
import app.network.http.parser.QueryFriendListParser;
import app.network.socket.SocketManager;
import app.servlet.util.EntityUtil;
import app.servlet.util.GsonUtil;
import app.servlet.util.RequestDispatcher;
import engine.java.util.CalendarFormat;
import engine.java.util.io.IOUtil;
import engine.java.util.log.LogFactory;
import engine.java.util.log.LogFactory.LOG;
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

/**
 * App处理器
 */
public class AppServlet extends HttpServlet {
    
    /** 测试用途，保证流程走通 **/
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
		LogFactory.enableLOG(!isTest);
	}
	
	private void initParser() {
	    // 获取导航
	    RequestDispatcher.register("navigation", NavigationParser.class);
	    // 用户登录
	    RequestDispatcher.register("login", LoginParser.class);
	    // 获取个人信息
	    RequestDispatcher.register("get_user_info", GetUserInfoParser.class);
	    // 查询好友列表
	    RequestDispatcher.register("query_friend_list", QueryFriendListParser.class);
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