package me.michalsmoladev.LibrarySystem.User.Application;

import me.michalsmoladev.LibrarySystem.User.Application.Command.CreateUser.CreateUserCommand;
import me.michalsmoladev.LibrarySystem.User.Application.Command.RemoveUser.RemoveUserCommand;
import me.michalsmoladev.LibrarySystem.User.Application.Command.UpdateUser.DTO.UpdateUserDTO;
import me.michalsmoladev.LibrarySystem.User.Application.Command.UpdateUser.UpdateUserCommand;
import me.michalsmoladev.LibrarySystem.User.Application.Exception.UserNotFoundException;
import me.michalsmoladev.LibrarySystem.User.Application.Query.GetUserById.DTO.GetUserByIdDTO;
import me.michalsmoladev.LibrarySystem.User.Application.Query.GetUserById.GetUserByIdQuery;
import me.michalsmoladev.LibrarySystem.User.Domain.Entity.User;
import me.michalsmoladev.LibrarySystem.User.Domain.Entity.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    private UserRepositoryInterface userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Logger logger = Logger.getLogger(UserService.class.getName());

    public void createUser(CreateUserCommand createUserCommand) {
        User user = new User();
        user.setUsername(createUserCommand.getCreateUserDTO().username);
        user.setEmail(createUserCommand.getCreateUserDTO().email);
        user.setPassword(passwordEncoder.encode(createUserCommand.getCreateUserDTO().password));
        user.setRole("ROLE_ADMIN");

        this.userRepository.save(user);
    }

    public void updateUser(UpdateUserCommand updateUserCommand) {
        User user = this.userRepository.findById(updateUserCommand.getId()).orElseThrow(() -> {
            this.logger.info(String.format("[UserUpdate] User with id %s not found", updateUserCommand.getId()));

            return new UserNotFoundException("User not found");
        });

        UpdateUserDTO updateUserDTO = updateUserCommand.getUpdateUserDTO();

        user.setUsername(updateUserDTO.username);
        user.setEmail(updateUserDTO.email);
        user.setPassword(passwordEncoder.encode(updateUserDTO.password));

        this.userRepository.save(user);
    }

    public void removeUser(RemoveUserCommand command) {
        this.userRepository.deleteById(command.getId());
    }

    public GetUserByIdDTO getUserById(GetUserByIdQuery query) {
        User user = this.userRepository.findById(query.getId()).orElseThrow(() -> {
            this.logger.info(String.format("[UserUpdate] User with id %s not found", query.getId()));

            return new UserNotFoundException("User not found");
        });

        return new GetUserByIdDTO(
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
