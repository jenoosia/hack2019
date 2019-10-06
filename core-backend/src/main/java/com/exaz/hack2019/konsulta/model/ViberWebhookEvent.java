package com.exaz.hack2019.konsulta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViberWebhookEvent {

    /*
    {
    "event": "message",
    "timestamp": 1570329737086,
    "chat_hostname": "SN-CHAT-01_",
    "message_token": 5361461199478174413,
    "sender": {
        "id": "jAroztUssBNJWDo5Et6FTg==",
        "name": "Jensen",
        "avatar": "https://media-direct.cdn.viber.com/download_photo?dlid=YmMjW5PXpwiRaWVKcpo7CBRsRGh2h-yZ2qAbrbOWr1yMDjloyZlbP9vOGuOUk9ev5XynfCgA_jRnwYTXu9UlLkJ1-w3HzR0mUsVdKsNpi_UWvCykf4s7_6g_j327ozCc31-k0Q&fltp=jpg&imsz=0000",
        "language": "en",
        "country": "SG",
        "api_version": 8
    },
    "message": {
        "text": "Hi",
        "type": "text"
    },
    "silent": false
}

{
    "event": "delivered",
    "timestamp": 1570331339107,
    "chat_hostname": "SN-CHAT-01_",
    "message_token": 5361467900326986371,
    "user_id": "jAroztUssBNJWDo5Et6FTg=="
}
     */
    private String event;
    private long timestamp;
    private String chat_hostname;
    private String message_token;
    private String user_id;
    private VWESender sender;
    private VWEMessage message;
    private Boolean silent;

    public ViberWebhookEvent() {

    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }

    public ViberWebhookEvent(String event, long timestamp, String chat_hostname, String message_token, String user_id, VWESender sender, VWEMessage message, Boolean silent) {
        this.event = event;
        this.timestamp = timestamp;
        this.chat_hostname = chat_hostname;
        this.message_token = message_token;
        this.user_id = user_id;
        this.sender = sender;
        this.message = message;
        this.silent = silent;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getChat_hostname() {
        return chat_hostname;
    }

    public void setChat_hostname(String chat_hostname) {
        this.chat_hostname = chat_hostname;
    }

    public String getMessage_token() {
        return message_token;
    }

    public void setMessage_token(String message_token) {
        this.message_token = message_token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public VWESender getSender() {
        return sender;
    }

    public void setSender(VWESender sender) {
        this.sender = sender;
    }

    public VWEMessage getMessage() {
        return message;
    }

    public void setMessage(VWEMessage message) {
        this.message = message;
    }

    public Boolean getSilent() {
        return silent;
    }

    public void setSilent(Boolean silent) {
        this.silent = silent;
    }
}
