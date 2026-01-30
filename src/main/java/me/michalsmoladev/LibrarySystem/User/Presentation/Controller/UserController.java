package me.michalsmoladev.LibrarySystem.User.Presentation.Controller;

import me.michalsmoladev.LibrarySystem.Configuration.Application.Jwt.JwtService;
import me.michalsmoladev.LibrarySystem.User.Application.CreateUser.CreateUserCommand;
import me.michalsmoladev.LibrarySystem.User.Application.CreateUser.DTO.CreateUserDTO;
import me.michalsmoladev.LibrarySystem.User.Application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/api/user/create")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO) {
        this.userService.createUser(
                new CreateUserCommand(createUserDTO)
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/user/login")
    public String login() {
        return this.jwtService.generateToken(
          new UsernamePasswordAuthenticationToken(
                  "admin@example.com",
                  "admin1"
          )
        );
    }
}
