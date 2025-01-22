package com.springboot_practice.springboot_practice.ServiceImpl;

import com.springboot_practice.springboot_practice.Model.User;
import com.springboot_practice.springboot_practice.Repositorie.UserRepository;
import com.springboot_practice.springboot_practice.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {



    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return  user.get();
    }
}
