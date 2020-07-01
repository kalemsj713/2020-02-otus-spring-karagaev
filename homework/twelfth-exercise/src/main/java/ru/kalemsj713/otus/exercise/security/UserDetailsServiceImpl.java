package ru.kalemsj713.otus.exercise.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Role;
import ru.kalemsj713.otus.exercise.domain.User;
import ru.kalemsj713.otus.exercise.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        Set<GrantedAuthority> grants = new HashSet<>();
        for (Role role : applicationUser.get().getRoles()) {
            grants.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(applicationUser.get().getLogin(), applicationUser.get().getPassword(),
                grants);

    }


}
