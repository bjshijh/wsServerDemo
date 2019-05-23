package com.example.wsServerDemo.entity;

/**
 *
 * @author Shijh
 */
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
public class Instruction {
    long commandId;
    long timestamp;
    String commandText;
    Map<String,Object> params;
    
    public Instruction(){
        // retieve commandid from redis/database
        
        timestamp=new Date().getTime();
        params = new HashMap<String, Object>();
    }
    
    public long getCommandId() { return commandId; }
    
    public String getCommandText() { return commandText; }
    public void setCommandText(String text) { this.commandText=text; }
    
    public Map<String, Object> getCommandParameters() {
        return params;
    }
    public Object getCommandParameter(String pname) {
        return params.get(pname);
    }
    
    public void addCommandParameter(String pname, Object pvalue) {
        params.put(pname, pvalue);
    }
    
    public long getTimestamp() { return timestamp; }
}
