package com.example.wsServerDemo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import javax.websocket.Session;
import java.util.Set;
import java.util.HashSet;
import com.example.wsServerDemo.entity.EdiAgent;
import com.example.wsServerDemo.websocket.WsSessions;

@RestController
@RequestMapping("/agent")
public class AgentAdmin {

    @RequestMapping("/getAll")
    public Set<EdiAgent> getAllAgents() {
        Set<EdiAgent> agents = new HashSet<>();
        for (EdiAgent agent : WsSessions.webSocketSet.values()) {
            agents.add(agent);
        }
        return agents;
    }
}
