package com.exaz.hack2019.konsulta.model;

public class ViberSend {

    private ViberSender sender;
    private String receiver;
    private Integer min_api_version = 1;
    private String tracking_data = "konsulta";
    private String type = "text";
    private String text;

    public ViberSend() {
    }

    public ViberSend(ViberSender sender, String receiver, Integer min_api_version, String tracking_data, String type, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.min_api_version = min_api_version;
        this.tracking_data = tracking_data;
        this.type = type;
        this.text = text;
    }

    public ViberSender getSender() {
        return sender;
    }

    public void setSender(ViberSender sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getMin_api_version() {
        return min_api_version;
    }

    public void setMin_api_version(Integer min_api_version) {
        this.min_api_version = min_api_version;
    }

    public String getTracking_data() {
        return tracking_data;
    }

    public void setTracking_data(String tracking_data) {
        this.tracking_data = tracking_data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
