package app.servlet;

import static app.servlet.util.RequestDispatcher.register;
import app.network.http.parser.AddFriendParser;
import app.network.http.parser.EditUserInfoParser;
import app.network.http.parser.GetUserInfoParser;
import app.network.http.parser.LoginParser;
import app.network.http.parser.NavigationParser;
import app.network.http.parser.QueryFriendListParser;
import app.network.http.parser.RegisterParser;
import app.network.http.parser.SearchContactParser;
import app.network.socket.SocketManager;
import app.servlet.util.RequestDispatcher;
import app.util.EntityUtil;
import app.util.GsonUtil;
import engine.java.common.CalendarFormat;
import engine.java.common.LogFactory;
import engine.java.common.LogFactory.LOG;
import engine.java.util.io.IOUtil;

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
		LogFactory.enableLOG(!AppConfig.IS_TESTING);
	}
	
	private void initParser() {
	    // 获取导航配置
	    register("navigation", NavigationParser.class);
	    // 用户登录
	    register("login", LoginParser.class);
        // 用户注册
        register("register", RegisterParser.class);
	    // 获取个人信息
	    register("get_user_info", GetUserInfoParser.class);
        // 修改个人信息
        register("edit_user_info", EditUserInfoParser.class);
	    // 查询好友列表
	    register("query_friend_list", QueryFriendListParser.class);
        // 搜索联系人
        register("search_contact", SearchContactParser.class);
        // 添加删除好友
        register("add_friend", AddFriendParser.class);
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
	    log(String.format("收到来自%s的请求:", req.getRemoteAddr()));
	    
	    String response;
	    try {
            String request = EntityUtil.toString(IOUtil.readStream(req.getInputStream()));
            log(request);
            
            JSONObject json = new JSONObject(request);
            response = RequestDispatcher.dispatch(json.getString("action"), json);
        } catch (Exception e) {
            LOG.log(e);
            response = GsonUtil.toJson(new ErrorInfo(400));
        }
	    
	    log(String.format("响应来自%s的请求:", req.getRemoteAddr()));
        log(response);
        
	    resp.setContentType("text/json;charset=UTF-8");
	    resp.getOutputStream().write(EntityUtil.toByteArray(response));
	    resp.getOutputStream().flush();
	}
}