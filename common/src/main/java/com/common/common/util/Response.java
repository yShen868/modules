package com.common.common.util;

public class Response<T> {
    private int status;
    private String message;
    private T data;

    private long timestamp;
    private String logId;

    public Response() {
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public String getLogId() {
        return LogUtils.getPreLog();
    }
}
