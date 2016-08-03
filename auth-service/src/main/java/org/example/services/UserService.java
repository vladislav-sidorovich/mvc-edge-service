package org.example.services;

import org.example.details.ExtendedUserDetails;
import org.example.domain.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username, "User name should no be null");

        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " was not found.");
        }

        return new ExtendedUserDetails(user);
    }

    public User create(User user) {
        User persistedUser = repository.save(user);
        return persistedUser;
    }
}
