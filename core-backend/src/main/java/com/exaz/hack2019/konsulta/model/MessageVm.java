package com.exaz.hack2019.konsulta.model;

public class MessageVm {

    private String id;
    private String channel;
    private String message;
    private boolean fromPatient;
    private String dateSent;

    public MessageVm() {
    }

    public MessageVm(String id, String channel, String message, boolean fromPatient, String dateSent) {
        this.id = id;
        this.channel = channel;
        this.message = message;
        this.fromPatient = fromPatient;
        this.dateSent = dateSent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFromPatient() {
        return fromPatient;
    }

    public void setFromPatient(boolean fromPatient) {
        this.fromPatient = fromPatient;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }
}
