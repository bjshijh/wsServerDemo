package com.example.wsServerDemo.component;

import javax.websocket.Session;
import com.example.wsServerDemo.websocket.WsSessions;
import com.example.wsServerDemo.entity.EdiAgent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EdiAgentService implements Runnable {
    public Session clientSocket;
    EdiAgent agent;
    
    public EdiAgentService(Session socketSession ) {
        this.clientSocket= socketSession;       
        this.agent=WsSessions.webSocketSet.get(socketSession);
    }


    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep( agent.interval*1000);
                log.info("Sending command () to agent " + agent.agentId +":" + agent.agentName);
                if (agent.status==0) {
                    clientSocket.getBasicRemote().sendText("sendDataToServer");
                    agent.status=1;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
