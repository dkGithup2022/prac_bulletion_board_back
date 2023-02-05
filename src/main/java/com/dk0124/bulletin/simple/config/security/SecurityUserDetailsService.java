package com.dk0124.bulletin.simple.config.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SecurityUserDetailsService implements  UserDetailsService {
    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User("dk0124@gmail.com",
                    "2378",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")
                    )
            ),
            new User("dk0123@gmail.com",
                    "2378",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")
                    )
            )
    );
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return APPLICATION_USERS.stream().filter(u -> u.getUsername().equals(email)).findFirst().orElseThrow(
                () -> {
                    System.out.println("no email");
                    throw new UsernameNotFoundException("No user found like : " + email);
                }
        );
    }

}
