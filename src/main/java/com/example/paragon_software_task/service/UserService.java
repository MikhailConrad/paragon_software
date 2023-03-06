package com.example.paragon_software_task.service;

import com.example.paragon_software_task.model.dto.AddUserRequest;
import com.example.paragon_software_task.model.dto.UserResponse;
import com.example.paragon_software_task.model.entity.Status;
import com.example.paragon_software_task.model.dto.StatusChangingRequest;
import com.example.paragon_software_task.model.dto.StatusChangingResponse;
import com.example.paragon_software_task.model.entity.User;
import com.example.paragon_software_task.exception.UserNotFoundException;
import com.example.paragon_software_task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final static int fiveMinutesInMillis = 300000;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserResponse findUser(int id) {

        return mapToUserResponse(userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    public int addUser(AddUserRequest request) {

        User user = new User(request.getUsername(), request.getEmail(), request.getPhoneNumber());
        user.setStatus(Status.OFFLINE);
        user.setDateOfStatusChange(LocalDateTime.now());
        return userRepository.save(user).getId();
    }

    public StatusChangingResponse updateUserStatus(StatusChangingRequest statusChangingRequest) {

        User user = userRepository.findById(statusChangingRequest.getUserId()).orElseThrow(UserNotFoundException::new);
        StatusChangingResponse statusChangingResponse = new StatusChangingResponse(
                user.getId(),
                statusChangingRequest.getNewStatus(),
                user.getStatus()
        );
        user.setStatus(statusChangingRequest.getNewStatus());
        user.setDateOfStatusChange(LocalDateTime.now());
        userRepository.save(user);

        if (statusChangingRequest.getNewStatus().equals(Status.ONLINE)) {
            changeUserStatusToAwayAfter5Min(user, fiveMinutesInMillis);
        }

        return statusChangingResponse;
    }

    public void changeUserStatusToAwayAfter5Min(User userToStatusUpdate, int time) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                User currentUserState = userRepository.findById(userToStatusUpdate.getId())
                        .orElseThrow(() -> new UserNotFoundException("Невозможно обновить статус. User с ID: "
                                + userToStatusUpdate.getId() + " не существует"));

                if (currentUserState.getDateOfStatusChange().getMinute() == userToStatusUpdate.getDateOfStatusChange().getMinute()
                        && currentUserState.getDateOfStatusChange().getSecond() == userToStatusUpdate.getDateOfStatusChange().getSecond()
                        && currentUserState.getStatus().equals(Status.ONLINE)) {

                    currentUserState.setStatus(Status.AWAY);
                    currentUserState.setDateOfStatusChange(LocalDateTime.now());
                    userRepository.save(currentUserState);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, time);
    }

    private UserResponse mapToUserResponse (User user) {
        return new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getDateOfStatusChange(),
                user.getStatus().toString()
        );
    }
}
