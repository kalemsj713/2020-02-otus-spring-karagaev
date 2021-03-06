package ru.kalemsj713.otus.exercise.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.User;
import ru.kalemsj713.otus.exercise.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> applicationUser = userRepository.findUserByLogin(username);
        if (applicationUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser.get();
    }

}
