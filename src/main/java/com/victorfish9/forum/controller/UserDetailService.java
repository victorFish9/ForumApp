package com.victorfish9.forum.controller;

import com.victorfish9.forum.models.User;
import com.victorfish9.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User curruser = repository.findByUsername(username);

        if (curruser == null){
            throw new UsernameNotFoundException("User not found with username " + username);
        }

        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        return user;
    }
}
