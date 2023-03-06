package com.example.paragon_software_task.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddUserRequest {

    @NotBlank
    @Pattern(regexp = "^[A-Za-zА-Яа-я-]+$", message = "В имени использованы недопустимые символы")
    private String username;
    @Email(message = "Некорректно введен e-mail")
    private String email;
    @Pattern(regexp = "^(\\+|\\d)\\d{7,15}$", message = "В номере телефона допустимо использование только цифр и + в начале")
    private String phoneNumber;

    public AddUserRequest(String username, String email, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
}
