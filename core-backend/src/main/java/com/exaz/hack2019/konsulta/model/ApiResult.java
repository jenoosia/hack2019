package com.exaz.hack2019.konsulta.model;

public class ApiResult {

    private boolean success = false;
    private String message;

    public ApiResult() {
    }

    public ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
