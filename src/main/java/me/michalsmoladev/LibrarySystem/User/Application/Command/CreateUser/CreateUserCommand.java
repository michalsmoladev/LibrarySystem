package me.michalsmoladev.LibrarySystem.User.Application.Command.CreateUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.michalsmoladev.LibrarySystem.User.Application.Command.CreateUser.DTO.CreateUserDTO;

@AllArgsConstructor
@Getter
public class CreateUserCommand {
    private CreateUserDTO createUserDTO;
}
