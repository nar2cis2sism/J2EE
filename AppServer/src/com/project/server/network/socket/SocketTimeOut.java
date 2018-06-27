package com.project.server.network.socket;

import com.project.app.bean.User;
import com.project.app.util.UserManager;

import engine.java.util.extra.Singleton;
import engine.java.util.log.LogFactory.LOG;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class SocketTimeOut implements SocketParam, Runnable {
    
    private static final Singleton<SocketTimeOut> instance
    = new Singleton<SocketTimeOut>() {
        
        @Override
        protected SocketTimeOut create() {
            return new SocketTimeOut();
        }
    };
    
    public static final SocketTimeOut getInstance() {
        return instance.get();
    }

    /******************************* 华丽丽的分割线 *******************************/
    
    private final LinkedHashMap<Long, Long> timeout
    = new LinkedHashMap<Long, Long>();
    
    private final ScheduledExecutorService timer
    = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "socket连接计时器");

            t.setDaemon(true);
            t.setPriority(Thread.NORM_PRIORITY - 1);

            return t;
        }
    });
    
    private final AtomicBoolean isRunning = new AtomicBoolean();
    
    private SocketTimeOut() {}
    
    public void active(long uid) {
        timeout.remove(uid);
        timeout.put(uid, System.currentTimeMillis() + TIMEOUT);
        if (isRunning.compareAndSet(false, true))
        {
            timer.schedule(this, TIMEOUT, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        Iterator<Entry<Long, Long>> iter = timeout.entrySet().iterator();
        while (iter.hasNext())
        {
            Entry<Long, Long> entry = iter.next();
            long delay = entry.getValue() - time;
            if (delay <= 0)
            {
                iter.remove();
                // 超时后断开连接
                User user = UserManager.getUser(entry.getKey());
                if (user != null)
                {
                    user.setSocketConnection(null);
                    LOG.log("socket超时断开连接-" + user.info.getUid());
                }
            }
            else
            {
                timer.schedule(this, delay, TimeUnit.MILLISECONDS);
                return;
            }
        }
        
        isRunning.set(false);
    }
}