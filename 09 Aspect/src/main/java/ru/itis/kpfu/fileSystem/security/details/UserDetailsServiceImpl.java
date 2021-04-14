package ru.itis.kpfu.fileSystem.security.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.fileSystem.repositories.interfaces.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var userCandidate = userRepository.findUserByEmail(email);
        if (userCandidate.isPresent()){
            return new UserDetailsImpl(userCandidate.get());
        } else throw new UsernameNotFoundException("User not found");
    }
}
