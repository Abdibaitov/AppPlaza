package peaksoft.appplaza.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import peaksoft.appplaza.repository.UserRepository;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository.findByEmail(username).
                    orElseThrow(() -> new UsernameNotFoundException("User not found with name " + username));
    }
}
