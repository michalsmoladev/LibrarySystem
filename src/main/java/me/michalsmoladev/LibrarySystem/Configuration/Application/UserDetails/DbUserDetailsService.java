package me.michalsmoladev.LibrarySystem.Configuration.Application.UserDetails;

import me.michalsmoladev.LibrarySystem.User.Domain.Entity.UserRepositoryInterface;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DbUserDetailsService implements UserDetailsService {
    private UserRepositoryInterface userRepository;

    public DbUserDetailsService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
