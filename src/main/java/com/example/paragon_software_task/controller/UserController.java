package com.example.paragon_software_task.controller;

import com.example.paragon_software_task.entity.StatusChangingRequestDTO;
import com.example.paragon_software_task.entity.StatusChangingResponseDTO;
import com.example.paragon_software_task.entity.User;
import com.example.paragon_software_task.service.UserService;
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
    public User findUser(@PathVariable int id) {
        return userService.findUser(id);
    }

    @PostMapping
    public int addUser(@RequestBody User user) {
        return userService.addUser(user).getId();
    }

    @PutMapping("/status")
    public StatusChangingResponseDTO updateUserStatus(@RequestBody StatusChangingRequestDTO statusChangingRequest) {

        return userService.updateUserStatus(statusChangingRequest);
    }
}
