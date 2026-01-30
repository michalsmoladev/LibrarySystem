package me.michalsmoladev.LibrarySystem.User.Application.CreateUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.michalsmoladev.LibrarySystem.User.Application.CreateUser.DTO.CreateUserDTO;

@AllArgsConstructor
@Getter
public class CreateUserCommand {
    private CreateUserDTO createUserDTO;
}
