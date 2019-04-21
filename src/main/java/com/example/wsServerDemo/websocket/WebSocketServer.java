package com.example.wsServerDemo.websocket;

import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;

import javax.websocket.EndpointConfig;
import javax.websocket.server.ServerEndpoint;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import java.util.Date;
import com.google.gson.Gson;
import com.example.wsServerDemo.entity.EdiAgent;
import com.example.wsServerDemo.entity.TaskResult;
import com.example.wsServerDemo.component.EdiAgentTasks;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ServerEndpoint(value = "/websocket", configurator = HttpSessionConfigurator.class)
public class WebSocketServer {

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        HttpSession httpSession
                = (HttpSession) config.getUserProperties().get("HttpSession");

        log.info(session.getId() + " connected.");
        session.setMaxIdleTimeout(0);

        WsSessions.add(session, httpSession);
    }

    @OnClose
    public void onClose(Session session) {
        WsSessions.remove(session);
        EdiAgentTasks.removeTask(session);
        log.warn(session.getId() + " closed.");
    }

    @OnMessage
    public void onMessage(String message, Session sess) {
        log.info("Session: " + sess.getId() + ", Message: " + message);
        Gson gson = new Gson();
        if (message.startsWith("agentInfo")) {
            String objstr = message.substring("agentInfo".length() + 1);
            EdiAgent agent = gson.fromJson(objstr, EdiAgent.class);

            WsSessions.webSocketSet.put(sess, agent);
            EdiAgentTasks.addAgentTask(sess);
        } else if (message.startsWith("taskResult")) {
            String objstr = message.substring("taskResult".length() + 1);
            TaskResult tr = gson.fromJson(objstr, TaskResult.class);
            EdiAgent agent = WsSessions.webSocketSet.get(sess);
            agent.status= 0; // ready for next job
        } else if (message.startsWith("ping")) {
            EdiAgent agent = WsSessions.webSocketSet.get(sess);
            agent.pingDttm = new Date();
        } else {

        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
        WsSessions.remove(session);
        EdiAgentTasks.removeTask(session);
    }
}
