package com.example.wsServerDemo.entity;


public class EdiDataSource {
	public String dataSourceType;  // HTTP, FTP, Database
	
	public String driverName, url, userName="", password=""; 
	public String retrievingSql;
	
	
	
	public void retrieveData() {
		
	}
}
