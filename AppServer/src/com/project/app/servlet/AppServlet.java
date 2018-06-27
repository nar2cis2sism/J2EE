package com.project.app.servlet;

import com.project.app.AppConfig;
import com.project.app.action.ActionRegister;
import com.project.app.servlet.util.RequestDispatcher;
import com.project.server.network.socket.SocketManager;
import com.project.util.GsonUtil;

import engine.java.util.common.CalendarFormat;
import engine.java.util.io.IOUtil;
import engine.java.util.log.LogFactory;
import engine.java.util.log.LogFactory.LOG;

import org.json.JSONObject;

import protocol.java.EntityUtil;
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
		ActionRegister.init();
		SocketManager.setup();
	}
	
	private void initLog() {
		LogFactory.init(new File(getServletContext().getRealPath("logs"), 
				CalendarFormat.format(Calendar.getInstance(), "yyyyMMdd_HHmmss")));
		LogFactory.enableLOG(!AppConfig.IS_TESTING);
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
            response = RequestDispatcher.dispatch(json.getString("action"), json);
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