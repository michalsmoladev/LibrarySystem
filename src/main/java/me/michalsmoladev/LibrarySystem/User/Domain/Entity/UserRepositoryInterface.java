package me.michalsmoladev.LibrarySystem.User.Domain.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryInterface extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
