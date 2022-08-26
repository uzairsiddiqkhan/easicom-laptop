package com.example.easicom.Models;

public class messagesModel {
    String message;
    String uId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    String messageId;
    Long timestamp;

    public messagesModel(String message, String uId, Long timestamp) {
        this.message = message;
        this.uId = uId;
        this.timestamp = timestamp;
    }

    public messagesModel(String message, String uId) {
        this.message = message;
        this.uId = uId;
    }

    public messagesModel(){}


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
