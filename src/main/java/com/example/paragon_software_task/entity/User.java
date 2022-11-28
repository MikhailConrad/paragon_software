package com.example.paragon_software_task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zА-Яа-я-]+$", message = "В имени использованы недопустимые символы")
    @Column(name = "username")
    private String username;

    @Email(message = "Некорректно введен e-mail")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^(\\+|\\d)\\d{7,15}$", message = "В номере телефона допустимо использование только цифр и + в начале")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_status_change")
    private LocalDateTime dateOfStatusChange;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public User(String username, String email, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfStatusChange = LocalDateTime.now();
        this.status = Status.OFFLINE;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && username.equals(user.username) && email.equals(user.email) && phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, phoneNumber);
    }
}
