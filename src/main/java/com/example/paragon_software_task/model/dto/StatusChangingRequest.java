package com.example.paragon_software_task.model.dto;

import com.example.paragon_software_task.model.entity.Status;

public class StatusChangingRequest {

    private int userId;
    private Status newStatus;

    public StatusChangingRequest(int userId, Status newStatus) {
        this.userId = userId;
        this.newStatus = newStatus;
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
}
