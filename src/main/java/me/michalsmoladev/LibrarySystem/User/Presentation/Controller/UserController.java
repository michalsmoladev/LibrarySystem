package me.michalsmoladev.LibrarySystem.User.Presentation.Controller;

import me.michalsmoladev.LibrarySystem.Configuration.Application.Jwt.JwtService;
import me.michalsmoladev.LibrarySystem.User.Domain.Entity.User;
import me.michalsmoladev.LibrarySystem.User.Domain.Entity.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepositoryInterface userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/api/user/create")
    public String createUser() {
        User user = new User();
        user.setEmail("admin@example.com");
        user.setPassword(this.passwordEncoder.encode("admin"));
        user.setUsername("admin");
        user.setRole("ROLE_ADMIN");

        this.userRepository.save(user);

        return user.getEmail();
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
