package com.example.wsServerDemo.entity;

/**
 *
 * @author Shijh: 从供应商发给施耐德的消息, 如: ping消息, ftp结果/出错消息
 */

public class SupplierMessage {
    String messageClass;
    String messageContent;
    
    public SupplierMessage(String msgcls, String content) {
        messageClass = msgcls;
        messageContent=content;
    }
    
    public String getMessageClass() { return this.messageClass; }
    public void setMessageClass(String msgcls) {
        this.messageClass = msgcls;
    }
    
    public String getMessageContent() { return this.messageContent; }
    public void setMessageContent(String content){
        this.messageContent=content;
    }    
}
