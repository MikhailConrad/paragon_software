package com.example.paragon_software_task.model.dto;

import java.time.LocalDateTime;

public class UserResponse {
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDateTime dateOfStatusChange;
    private String status;

    public UserResponse(String username, String email, String phoneNumber, LocalDateTime dateOfStatusChange, String status) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfStatusChange = dateOfStatusChange;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getDateOfStatusChange() {
        return dateOfStatusChange;
    }

    public void setDateOfStatusChange(LocalDateTime dateOfStatusChange) {
        this.dateOfStatusChange = dateOfStatusChange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
