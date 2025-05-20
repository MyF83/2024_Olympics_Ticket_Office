package com.myriamfournier.olympics_ticket_office.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.myriamfournier.olympics_ticket_office.pojo.users;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;


// UserDetailsServiceImpl.java is only responsible for loading user details from the database.
// It does not handle authentication or authorization logic.
// This is typically done in a separate service or controller.
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        users users = userRepository.findUserByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
            users.getUsername(),
            users.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(users.getRoles().getName()))
        );
    }
}
