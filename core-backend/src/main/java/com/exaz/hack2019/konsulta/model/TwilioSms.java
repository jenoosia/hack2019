package com.exaz.hack2019.konsulta.model;

import com.exaz.hack2019.konsulta.constant.MessageChannels;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class TwilioSms extends StandardMessage {


    /*
    [SAMPLE]
        "SmsMessageSid": "SMbffdc0f5de6bdb7b10f6215d48e55ed9",
        "NumMedia": "0",
        "SmsSid": "SMbffdc0f5de6bdb7b10f6215d48e55ed9",
        "SmsStatus": "received",
        "Body": "hello world",
        "To": "whatsapp:+14155238886",
        "NumSegments": "1",
        "MessageSid": "SMbffdc0f5de6bdb7b10f6215d48e55ed9",
        "AccountSid": "AC123062fbb3f754f89590d91c76e9815e",
        "From": "whatsapp:+6592389143",
        "ApiVersion": "2010-04-01"
     */

    private String From;
    private String To;
    private String AccountSid;

    private String SmsStatus;
    private String Body;

    private String MessageSid;
    private String SmsSid;
    private String SmsMessageSid;

    private String NumMedia;
    private String NumSegments;
    private String ApiVersion;

    private boolean wa;
    
    public TwilioSms() {
        this.channel = MessageChannels.Sms.name();
    }
    
    public TwilioSms(Map<String, String> msgMap) {
        this();
        this.SmsMessageSid = msgMap.get("");
        this.NumMedia = msgMap.get("NumMedia");
        this.SmsSid = msgMap.get("SmsSid");
        this.SmsStatus = msgMap.get("SmsStatus");
        this.Body = msgMap.get("Body");
        this.To = msgMap.get("To");
        this.NumSegments = msgMap.get("NumSegments");
        this.MessageSid = msgMap.get("MessageSid");
        this.AccountSid = msgMap.get("AccountSid");
        this.From = msgMap.get("From");
        this.ApiVersion = msgMap.get("ApiVersion");
        this.wa = StringUtils.isNotEmpty(this.From) && this.From.startsWith("whatsapp");
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }

    public String getSmsMessageSid() {
        return SmsMessageSid;
    }

    public void setSmsMessageSid(String smsMessageSid) {
        SmsMessageSid = smsMessageSid;
    }

    public String getNumMedia() {
        return NumMedia;
    }

    public void setNumMedia(String numMedia) {
        NumMedia = numMedia;
    }

    public String getSmsSid() {
        return SmsSid;
    }

    public void setSmsSid(String smsSid) {
        SmsSid = smsSid;
    }

    public String getSmsStatus() {
        return SmsStatus;
    }

    public void setSmsStatus(String smsStatus) {
        SmsStatus = smsStatus;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getNumSegments() {
        return NumSegments;
    }

    public void setNumSegments(String numSegments) {
        NumSegments = numSegments;
    }

    public String getMessageSid() {
        return MessageSid;
    }

    public void setMessageSid(String messageSid) {
        MessageSid = messageSid;
    }

    public String getAccountSid() {
        return AccountSid;
    }

    public void setAccountSid(String accountSid) {
        AccountSid = accountSid;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getApiVersion() {
        return ApiVersion;
    }

    public void setApiVersion(String apiVersion) {
        ApiVersion = apiVersion;
    }

    public boolean isWa() {
        return wa;
    }

    public void setWa(boolean wa) {
        this.wa = wa;
    }
}
