package me.michalsmoladev.LibrarySystem.User.Presentation.Controller;

import me.michalsmoladev.LibrarySystem.Configuration.Application.Jwt.JwtService;
import me.michalsmoladev.LibrarySystem.User.Application.CreateUser.CreateUserCommand;
import me.michalsmoladev.LibrarySystem.User.Application.CreateUser.DTO.CreateUserDTO;
import me.michalsmoladev.LibrarySystem.User.Application.LoginUser.DTO.LoginUserDTO;
import me.michalsmoladev.LibrarySystem.User.Application.LoginUser.DTO.LoginUserResponseDTO;
import me.michalsmoladev.LibrarySystem.User.Application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/user/create")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO) {
        this.userService.createUser(
                new CreateUserCommand(createUserDTO)
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/user/login")
    public ResponseEntity<LoginUserResponseDTO> login(@RequestBody LoginUserDTO loginUserDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.email,
                        loginUserDTO.password
                )
        );

        String token = jwtService.generateToken(authentication);

        return ResponseEntity.ok(new LoginUserResponseDTO(token));
    }
}
