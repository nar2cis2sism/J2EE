package com.project.server.network.socket;

import engine.java.common.LogFactory.LOG;
import engine.java.util.extra.ReflectObject;
import protocol.java.ProtocolWrapper.ProtocolEntity.ProtocolData;

import java.util.HashMap;

/**
 * 信令分发器
 */
public class SocketDispatcher {
    
    private static final HashMap<Integer, Class<? extends SocketParser<? extends ProtocolData>>> dispatcherMap
    = new HashMap<>();
    
    /**
     * 需要提前注册信令处理器
     * 
     * @param cmd 指令码
     * @param parserCls 指令解析类
     */
    public static void register(int cmd, Class<? extends SocketParser<? extends ProtocolData>> parserCls) {
        dispatcherMap.put(cmd, parserCls);
    }
	
    /**
     * 分发信令
     * 
     * @param cmd 指令码
     * @param data 请求数据
     * @return 应答数据
     */
	public static ProtocolData[] dispatch(int cmd, ProtocolData data) {
        try {
            Class<? extends SocketParser<? extends ProtocolData>> cls = dispatcherMap.get(cmd);
            if (cls == null)
            {
                throw new NullPointerException("没有注册信令解析器:" + data.getClass().getName());
            }
            
            SocketParser<? extends ProtocolData> parser = cls.newInstance();
            ReflectObject ref = new ReflectObject(parser);
            ref.invoke(ref.getMethod("parse", ProtocolData.class), data);
            return parser.ack;
        } catch (Exception e) {
            LOG.log(e);
        }
        
        return null;
	}
    
    public static abstract class SocketParser<T extends ProtocolData> {
        
    	ProtocolData[] ack;
        
        public abstract void parse(T data) throws Exception;
        
        protected final void setAck(ProtocolData... ack) {
            this.ack = ack;
        }
    }
}