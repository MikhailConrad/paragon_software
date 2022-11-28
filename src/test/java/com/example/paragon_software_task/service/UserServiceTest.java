package com.example.paragon_software_task.service;

import com.example.paragon_software_task.entity.Status;
import com.example.paragon_software_task.entity.StatusChangingRequestDTO;
import com.example.paragon_software_task.entity.StatusChangingResponseDTO;
import com.example.paragon_software_task.entity.User;
import com.example.paragon_software_task.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);

        User userInDb = new User("Alex", "alex@mail.ru", "8-900-000-00-00");

        Mockito.when(userRepository.findById(anyInt()))
                .thenReturn(Optional.of(userInDb));
        Mockito.when(userRepository.save(any()))
                .thenReturn(userInDb);

    }

    @Test
    public void findUserById() {
        //given
        User userForTest = new User("Alex", "alex@mail.ru", "8-900-000-00-00");
        int id = 1;

        //when
        User user = userService.findUser(id);

        //then
        Assertions.assertEquals(userForTest, user);
    }

    @Test
    public void addUser() {
        //given
        User userForTest = new User("Alex", "alex@mail.ru", "8-900-000-00-00");

        //when
        User user = userService.addUser(userForTest);

        //then
        Assertions.assertNotNull(userForTest.getDateOfStatusChange());
        Assertions.assertEquals(Status.OFFLINE, userForTest.getStatus());
        Assertions.assertNotEquals(userForTest.getDateOfStatusChange(), user.getDateOfStatusChange());
    }

    @Test
    public void updateUserStatus() {
        //given
        int id = 1;

        //when
        StatusChangingRequestDTO statusChangingRequest = new StatusChangingRequestDTO(id, Status.AWAY);
        StatusChangingResponseDTO statusChangingResponse = userService.updateUserStatus(statusChangingRequest);

        //then
        Assertions.assertEquals(Status.OFFLINE, statusChangingResponse.getOldStatus());
        Assertions.assertEquals(Status.AWAY, statusChangingResponse.getNewStatus());
    }
}