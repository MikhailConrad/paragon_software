package com.example.paragon_software_task.controller;

import com.example.paragon_software_task.model.dto.StatusChangingRequest;
import com.example.paragon_software_task.model.dto.StatusChangingResponse;
import com.example.paragon_software_task.model.dto.AddUserRequest;
import com.example.paragon_software_task.model.dto.UserResponse;
import com.example.paragon_software_task.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse findUser(@PathVariable int id) {
        return userService.findUser(id);
    }

    @PostMapping
    public int addUser(@RequestBody @Valid AddUserRequest user) {
        return userService.addUser(user);
    }

    @PutMapping("/status")
    public StatusChangingResponse updateUserStatus(@RequestBody StatusChangingRequest statusChangingRequest) {

        return userService.updateUserStatus(statusChangingRequest);
    }
}
