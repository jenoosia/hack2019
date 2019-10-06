package com.exaz.hack2019.konsulta.model;

import java.util.List;

public class CaseVm {

    private String id;
    private String name;
    private String caseRefNum;
    private String mobileNum;
    private String address;

    private List<MessageVm> messages;

    public CaseVm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseRefNum() {
        return caseRefNum;
    }

    public void setCaseRefNum(String caseRefNum) {
        this.caseRefNum = caseRefNum;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MessageVm> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageVm> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
