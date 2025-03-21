package ru.ixlax.TodoWebApp.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ixlax.TodoWebApp.models.user.User;
import ru.ixlax.TodoWebApp.models.user.UserDetailsImpl;
import ru.ixlax.TodoWebApp.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Email '%s' not found", email)
                ));
        return UserDetailsImpl.build(user);
    }
}
