package com.project.app.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import protocol.java.json.ErrorInfo;

import com.project.app.AppConfig;
import com.project.app.action.ActionRegister;
import com.project.app.servlet.util.EntityUtil;
import com.project.app.servlet.util.RequestDispatcher;
import com.project.server.network.socket.SocketManager;
import com.project.util.GsonUtil;

import engine.java.common.CalendarFormat;
import engine.java.common.LogFactory;
import engine.java.common.LogFactory.LOG;
import engine.java.util.io.IOUtil;

/**
 * App处理器
 */
public class AppServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    @Override
	public void init() throws ServletException {
		initLog();
		registerAction();
		setupSocket();
	}
	
	private void initLog() {
		LogFactory.init(new File(getServletContext().getRealPath("logs"), 
				CalendarFormat.format(Calendar.getInstance(), "yyyyMMdd_HHmmss")));
		LogFactory.enableLOG(!AppConfig.IS_TESTING);
	}
	
	private void registerAction() {
		ActionRegister.init();
	}
	
	private void setupSocket() {
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
	
	@Override
	public void log(String msg) {
		LOG.log(msg);
	}
}