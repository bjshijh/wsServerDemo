package com.example.wsServerDemo.entity;

import java.util.Date;
public class EdiAgent {
	public int agentId=-1;
	
	public String agentName="Unknown";
	public String remoteIp;
	
	public int interval =60;
	
	public EdiDataSource dataSource;
        
        // 0: ready for new task; 1: executing a task
	public int status=0;
	
	public Date pingDttm=new Date();
	
}
