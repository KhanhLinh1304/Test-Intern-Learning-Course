package com.example.learning_intern.component.customUser;

import com.example.learning_intern.model.User;
import com.example.learning_intern.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (user.getRole() == 0) {
            authorities.add(new SimpleGrantedAuthority("STUDENT"));
        } else {
            authorities.add(new SimpleGrantedAuthority("INSTRUCTOR"));
        }

        return new CustomUserDetail(
                user.getEmail(),
                user.getPassword(),
                user.getId(),
                authorities);
    }
}
