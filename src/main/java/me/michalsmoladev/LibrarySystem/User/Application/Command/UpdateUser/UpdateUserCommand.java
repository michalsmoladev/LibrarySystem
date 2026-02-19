package me.michalsmoladev.LibrarySystem.User.Application.Command.UpdateUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.michalsmoladev.LibrarySystem.User.Application.Command.UpdateUser.DTO.UpdateUserDTO;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UpdateUserCommand {
    private UUID id;
    private UpdateUserDTO updateUserDTO;
}
