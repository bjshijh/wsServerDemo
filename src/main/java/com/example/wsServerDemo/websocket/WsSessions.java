package com.example.wsServerDemo.websocket;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;
import javax.servlet.http.HttpSession;
import com.example.wsServerDemo.entity.EdiAgent;

public class WsSessions {
    public static int onlineCount = 0;
    
    // web socket session的集合
    public static ConcurrentHashMap<Session, EdiAgent> webSocketSet
            = new ConcurrentHashMap <Session, EdiAgent>();
 
    // 一个HTTP session对应多个web socket session. (一个页面可能就有一个socket会话, 但是一个HTTP会话)
    // web socket session与HTTP session的对应
    public static ConcurrentHashMap<Session, HttpSession> ws_http_session_map
            = new ConcurrentHashMap <Session, HttpSession>();
    
    public static HttpSession getHttpSession(String wsid) {
        return ws_http_session_map.get(wsid);
    }
    
    public static void add(Session wssess, HttpSession httpsess){
        webSocketSet.put( wssess, new EdiAgent() );
        ws_http_session_map.put( wssess, httpsess);
    }
    public static void remove(Session sess){
        webSocketSet.remove(sess);
        ws_http_session_map.remove(sess);
    }
    
    public static void sendMessage(Session sess, String msg){
        try {
            if ( sess!=null && sess.isOpen())
                sess.getBasicRemote().sendText(msg);
        }catch (Exception e) {
            
        }
    }
    
    public static void broadcast(Session sess, String msg){
        String sid=sess.getId();
        try {
            for (Session s : webSocketSet.keySet() ) {
                if ( !s.getId().equals(sid)){
                    s.getBasicRemote().sendText(msg);
                }
            }
            sess.getBasicRemote().sendText("Your Message \""+msg + "\" has been broadcasted.");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
