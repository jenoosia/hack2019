package com.exaz.hack2019.konsulta.model;

import com.exaz.hack2019.konsulta.constant.MessageChannels;
import com.exaz.hack2019.konsulta.constant.MessageTypes;

public class StandardMessage {
    protected String channel = MessageChannels.NA.name();
    protected String chatName = "";
    protected String messageType = MessageTypes.Text.name();

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
