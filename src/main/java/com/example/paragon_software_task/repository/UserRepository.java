package com.example.paragon_software_task.repository;

import com.example.paragon_software_task.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int userId);

    User save(User user);

}
