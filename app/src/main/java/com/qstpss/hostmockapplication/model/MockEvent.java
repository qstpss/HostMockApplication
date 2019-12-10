package com.qstpss.hostmockapplication.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

public class MockEvent {

    private MockEvent(){
    }

    public MockEvent(@NonNull Type type) {
        this.type = type;
        this.status = Status.PENDING;
    }

    @SerializedName("id")
    private Long id;

    @SerializedName("type")
    private Type type;

    @SerializedName("status")
    private Status status;

    @SerializedName("registerTimestamp")
    private Date registerTimestamp;

    @SerializedName("startTimestamp")
    private Date startTimestamp;

    @SerializedName("endTimestamp")
    private Date endTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getRegisterTimestamp() {
        return registerTimestamp;
    }

    public void setRegisterTimestamp(Date registerTimestamp) {
        this.registerTimestamp = registerTimestamp;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Date getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Date endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
