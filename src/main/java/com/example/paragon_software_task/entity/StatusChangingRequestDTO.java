package com.example.paragon_software_task.entity;

public class StatusChangingRequestDTO {

    private int userId;
    private Status newStatus;

    public StatusChangingRequestDTO(int userId, Status newStatus) {
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
