package com.zjr.dater.websocket;

import com.zjr.dater.business.model.User;
import com.zjr.dater.common.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by zhujr on 2018/10/17.
 * websocket服务端
 */
@Component
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer {
    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    //静态变量，用于记录当前在线连接数（应该设计为线程安全）
    private static int onlineCount = 0;
    //concurrent包的线程安全set，用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接对话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String sid = "";

    /**
     * 连接建立成功调用的方法
     * @param session
     * @param sid
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid){
        this.session = session;
        webSocketSet.add(this);//加入set中
        addOnlineCount();//在线人数+1
        logger.info("有新窗口开始监听："+sid+",当前在线人数为"+getOnlineCount());
        this.sid = sid;
        try {
            sendMessage("连接成功");
        }catch (IOException e){
            logger.error("websocket IO异常");
        }

    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
        logger.info("有一连接关闭！当前在线人数为"+getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        logger.info("收到来自窗口"+sid+"的信息："+message);
        //群发消息
        for (WebSocketServer item : webSocketSet){
            try {
                item.sendMessage(message);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        logger.error("发送错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * @return
     */
    public static void sendInfo(String message,@PathParam("sid")String sid) throws IOException{
        logger.info("推送消息到窗口"+sid+"，推送内容："+message);
        for (WebSocketServer item : webSocketSet){
            try {
                if(sid == null){
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            }catch (IOException e){
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }

    public static synchronized void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount(){
        WebSocketServer.onlineCount--;
    }

}
