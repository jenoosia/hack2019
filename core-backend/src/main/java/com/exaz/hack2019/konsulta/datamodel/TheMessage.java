package com.exaz.hack2019.konsulta.datamodel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TheMessage")
public class TheMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "createdDate", nullable = false)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caseRecordId", nullable = false)
    private CaseRecord caseRecord;

    @Column(name = "caseRefNum", nullable = false)
    private String caseRefNum;

    @Column(name = "raw", nullable = false)
    private String raw;

    @Column(name = "channel", nullable = false)
    private String channel;

    @Column(name = "msgSid", nullable = false)
    private String msgSid;

    @Column(name = "chatName")
    private String chatName;

    @Column(name = "fromSid")
    private String fromSid;

    @Column(name = "fromUser")
    private String fromUser;

    @Column(name = "toUser")
    private String toUser;

    //TODO Avatar is currently used for denoting staff or not.
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "message")
    private String message;

    @Column(name = "messageType")
    private String messageType;

    public TheMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMsgSid() {
        return msgSid;
    }

    public void setMsgSid(String msgSid) {
        this.msgSid = msgSid;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getFromSid() {
        return fromSid;
    }

    public void setFromSid(String fromSid) {
        this.fromSid = fromSid;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public CaseRecord getCaseRecord() {
        return caseRecord;
    }

    public void setCaseRecord(CaseRecord caseRecord) {
        this.caseRecord = caseRecord;
    }

    public String getCaseRefNum() {
        return caseRefNum;
    }

    public void setCaseRefNum(String caseRefNum) {
        this.caseRefNum = caseRefNum;
    }
}
