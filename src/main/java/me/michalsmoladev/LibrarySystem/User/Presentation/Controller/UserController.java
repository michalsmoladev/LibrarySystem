package me.michalsmoladev.LibrarySystem.User.Presentation.Controller;

import me.michalsmoladev.LibrarySystem.Configuration.Application.Jwt.JwtService;
import me.michalsmoladev.LibrarySystem.User.Application.Command.CreateUser.CreateUserCommand;
import me.michalsmoladev.LibrarySystem.User.Application.Command.CreateUser.DTO.CreateUserDTO;
import me.michalsmoladev.LibrarySystem.User.Application.Command.LoginUser.DTO.LoginUserDTO;
import me.michalsmoladev.LibrarySystem.User.Application.Command.LoginUser.DTO.LoginUserResponseDTO;
import me.michalsmoladev.LibrarySystem.User.Application.Command.RemoveUser.RemoveUserCommand;
import me.michalsmoladev.LibrarySystem.User.Application.Command.UpdateUser.DTO.UpdateUserDTO;
import me.michalsmoladev.LibrarySystem.User.Application.Command.UpdateUser.UpdateUserCommand;
import me.michalsmoladev.LibrarySystem.User.Application.Query.GetUserById.DTO.GetUserByIdDTO;
import me.michalsmoladev.LibrarySystem.User.Application.Query.GetUserById.GetUserByIdQuery;
import me.michalsmoladev.LibrarySystem.User.Application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PatchMapping("/api/user/{id}")
    public ResponseEntity<Void> updateUserAction(
            @PathVariable UUID id,
            @RequestBody UpdateUserDTO updateUserDTO
    ) {
        this.userService.updateUser(
                new UpdateUserCommand(id, updateUserDTO)
        );

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<Void> removeUserAction(@PathVariable UUID id) {
        this.userService.removeUser(
                new RemoveUserCommand(id)
        );

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<GetUserByIdDTO> getUserById(@PathVariable UUID id) {
        GetUserByIdDTO userDTO = this.userService.getUserById(
                new GetUserByIdQuery(id)
        );

        return ResponseEntity.ok(userDTO);
    }
}
