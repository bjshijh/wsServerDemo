package com.example.wsServerDemo.websocket;

import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletRequestEvent;
import org.springframework.stereotype.Component;

@Component
public class RequestListener implements ServletRequestListener {
	@Override  
    public void requestInitialized(ServletRequestEvent sre)  {  
        //将所有request请求都携带上httpSession  
        HttpSession session = ((HttpServletRequest) sre.getServletRequest()).getSession();  
    }  
	
    public RequestListener() {}  

    @Override  
    public void requestDestroyed(ServletRequestEvent arg0)  {}  
}
