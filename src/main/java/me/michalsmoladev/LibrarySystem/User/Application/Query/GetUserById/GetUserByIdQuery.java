package me.michalsmoladev.LibrarySystem.User.Application.Query.GetUserById;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GetUserByIdQuery {
    private UUID id;
}
