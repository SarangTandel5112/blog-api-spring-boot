package com.codewithdurgesh.blog.blogappapis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.blogappapis.entities.User;
import com.codewithdurgesh.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.blogappapis.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("============");
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email: " + username, 0));
        
        return user;
    }

}
