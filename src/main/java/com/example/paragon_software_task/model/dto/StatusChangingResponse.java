package com.example.paragon_software_task.model.dto;

import com.example.paragon_software_task.model.entity.Status;

public class StatusChangingResponse {

    private int userId;

    private Status newStatus;

    private Status oldStatus;

    public StatusChangingResponse(int userId, Status newStatus, Status oldStatus) {
        this.userId = userId;
        this.newStatus = newStatus;
        this.oldStatus = oldStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Status newStatus) {
        this.newStatus = newStatus;
    }

    public Status getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Status oldStatus) {
        this.oldStatus = oldStatus;
    }
}