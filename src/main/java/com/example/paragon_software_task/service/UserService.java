package com.example.paragon_software_task.service;

import com.example.paragon_software_task.entity.Status;
import com.example.paragon_software_task.entity.StatusChangingRequestDTO;
import com.example.paragon_software_task.entity.StatusChangingResponseDTO;
import com.example.paragon_software_task.entity.User;
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

    public User findUser(int userId) {

        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User addUser(User user) {

        user.setStatus(Status.OFFLINE);
        user.setDateOfStatusChange(LocalDateTime.now());
        return userRepository.save(user);
    }

    public StatusChangingResponseDTO updateUserStatus(StatusChangingRequestDTO statusChangingRequest) {

        User user = userRepository.findById(statusChangingRequest.getUserId()).orElseThrow(UserNotFoundException::new);
        StatusChangingResponseDTO statusChangingResponse = new StatusChangingResponseDTO(
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
        timer.schedule(timerTask, fiveMinutesInMillis);
    }
}
