package com.codeup.springblog.services;

import com.codeup.springblog.model.User;
import com.codeup.springblog.model.UserWithRoles;
import com.codeup.springblog.repo.UsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailLoader implements UserDetailsService {
    private final UsersRepo users;

    public UserDetailLoader (UsersRepo users){
        this.users = users;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user);
    }
}