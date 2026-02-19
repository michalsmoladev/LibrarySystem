package me.michalsmoladev.LibrarySystem.User.Application.Query.GetUserById.DTO;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class GetUserByIdDTO {
    public String username;
    public String email;
    public LocalDateTime createdAt;
}
