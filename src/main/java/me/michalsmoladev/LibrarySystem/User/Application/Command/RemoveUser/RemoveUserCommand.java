package me.michalsmoladev.LibrarySystem.User.Application.Command.RemoveUser;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class RemoveUserCommand {
    private UUID id;
}
