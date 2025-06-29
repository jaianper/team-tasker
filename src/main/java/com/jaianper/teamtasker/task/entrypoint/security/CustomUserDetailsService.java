package com.jaianper.teamtasker.task.entrypoint.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // TODO: Replace with actual user repository integration
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // This is a temporary implementation. In a real application, you would:
        // 1. Query the user repository
        // 2. Check if user exists
        // 3. Return UserDetails with proper authorities
        
        // For now, we'll create a simple admin user for testing
        if ("admin".equals(username)) {
            return new User(
                    "admin",
                    "$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi", // password: password
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        } else if ("member".equals(username)) {
            return new User(
                    "member",
                    "$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi", // password: password
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER"))
            );
        }
        
        throw new UsernameNotFoundException("User not found: " + username);
    }
} 