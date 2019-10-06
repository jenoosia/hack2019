package com.exaz.hack2019.konsulta.datamodel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CaseRecord")
public class CaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "createdDate", nullable = false)
    private Date createdDate;

    @Column(name = "caseRefNum", nullable = false)
    private String caseRefNum;

    @Column(name = "name")
    private String name;

    @Column(name = "primaryMobile")
    private String primaryMobile;

    @Column(name = "primaryChannel")
    private String primaryChannel;

    @Column(name = "viberId")
    private String viberId;

    @Column(name = "status")
    private String status;

    //TODO Being used for address
    @Column(name = "remarks")
    private String remarks;

    @Column(name = "channels")
    private String channels;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "caseRecord", targetEntity = TheMessage.class)
    private List<TheMessage> messages;

    public CaseRecord() {
    }

    public CaseRecord(int id, Date createdDate, String name, String primaryMobile, String primaryChannel, String viberId, String status, String remarks, String channels) {
        this.id = id;
        this.createdDate = createdDate;
        this.name = name;
        this.primaryMobile = primaryMobile;
        this.primaryChannel = primaryChannel;
        this.viberId = viberId;
        this.status = status;
        this.remarks = remarks;
        this.channels = channels;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryMobile() {
        return primaryMobile;
    }

    public void setPrimaryMobile(String primaryMobile) {
        this.primaryMobile = primaryMobile;
    }

    public String getPrimaryChannel() {
        return primaryChannel;
    }

    public void setPrimaryChannel(String primaryChannel) {
        this.primaryChannel = primaryChannel;
    }

    public String getViberId() {
        return viberId;
    }

    public void setViberId(String viberId) {
        this.viberId = viberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public List<TheMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<TheMessage> messages) {
        this.messages = messages;
    }

    public String getCaseRefNum() {
        return caseRefNum;
    }

    public void setCaseRefNum(String caseRefNum) {
        this.caseRefNum = caseRefNum;
    }
}
