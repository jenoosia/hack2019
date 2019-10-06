package com.exaz.hack2019.konsulta.model;

public class SendMessageVm {

    private String caseRefNum;
    private String message;
    private String staffName;

    public SendMessageVm() {
    }

    public SendMessageVm(String caseRefNum, String message, String staffName) {
        this.caseRefNum = caseRefNum;
        this.message = message;
        this.staffName = staffName;
    }

    public String getCaseRefNum() {
        return caseRefNum;
    }

    public void setCaseRefNum(String caseRefNum) {
        this.caseRefNum = caseRefNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
