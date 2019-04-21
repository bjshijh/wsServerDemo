package com.example.wsServerDemo.component;

import java.util.Map;
import java.util.HashMap;
import javax.websocket.Session;

public class EdiAgentTasks {
    public static Map<Session, Thread> agentTasks= new HashMap<>();
    
    public static void addAgentTask(Session sess){
        EdiAgentService agentService = new EdiAgentService(sess);
        Thread agentTaskThread = new Thread(agentService);
            
        agentTasks.put(sess, agentTaskThread);
        agentTaskThread.start();
    }
    
    public static void removeTask(Session sess) {
        Thread t = agentTasks.get(sess);
        if ( t!=null) {
            t.interrupt();
        }
        agentTasks.remove(sess);
    }
}
