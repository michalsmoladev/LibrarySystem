package me.michalsmoladev.LibrarySystem.User.Application;

import me.michalsmoladev.LibrarySystem.User.Application.CreateUser.CreateUserCommand;
import me.michalsmoladev.LibrarySystem.User.Domain.Entity.User;
import me.michalsmoladev.LibrarySystem.User.Domain.Entity.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepositoryInterface userRepository;

    public void createUser(CreateUserCommand createUserCommand) {
        User user = new User();
        user.setUsername(createUserCommand.getCreateUserDTO().username);
        user.setEmail(createUserCommand.getCreateUserDTO().email);
        user.setPassword(createUserCommand.getCreateUserDTO().password);

        this.userRepository.save(user);
    }
}
