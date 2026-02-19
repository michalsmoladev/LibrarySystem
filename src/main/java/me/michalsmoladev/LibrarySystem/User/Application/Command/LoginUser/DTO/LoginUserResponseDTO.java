package me.michalsmoladev.LibrarySystem.User.Application.Command.LoginUser.DTO;

public class LoginUserResponseDTO {
    public String token;

    public LoginUserResponseDTO(String token) {
        this.token = token;
    }
}
