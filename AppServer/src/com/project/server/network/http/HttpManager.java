package com.project.server.network.http;

import engine.java.http.HttpConnector;
import engine.java.http.HttpResponse;
import engine.java.util.extra.MyThreadFactory;
import engine.java.util.extra.Singleton;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpManager {
    
    private static final Singleton<HttpManager> instance
    = new Singleton<HttpManager>() {
        
        @Override
        protected HttpManager create() {
            return new HttpManager();
        }
    };
    
    public static final HttpManager getInstance() {
        return instance.get();
    }

    /******************************* 华丽丽的分割线 *******************************/
    
    private final ExecutorService httpThreadPool;
    
    private HttpManager() {
        httpThreadPool = Executors.newCachedThreadPool(new MyThreadFactory("Http网络连接"));
    }
    
    public void sendHttpRequestAsync(final HttpConnector conn) {
        httpThreadPool.submit(new Callable<HttpResponse>() {

            @Override
            public HttpResponse call() throws Exception {
                return conn.connect();
            }
        });
    }
}