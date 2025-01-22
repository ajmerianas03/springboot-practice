package com.springboot_practice.springboot_practice.Controller;

import com.springboot_practice.springboot_practice.DTO.QuestionDto;
import com.springboot_practice.springboot_practice.DTO.UserDto;
import com.springboot_practice.springboot_practice.Model.User;
import com.springboot_practice.springboot_practice.Response;
import com.springboot_practice.springboot_practice.Result;
import com.springboot_practice.springboot_practice.Service.UserLoginService;
import com.springboot_practice.springboot_practice.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/userLoginRegister/"})
public class UserLoginRegister {

    @Autowired
    private UserLoginService userService;


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);

    }

   /* @PostMapping("/login")
    public String login(@RequestBody User user) {

        return userService.verify(user);
    }*/

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        try {

            Map<String, Object> response = userService.verify(user);


            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {


            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Login failed", "message", e.getMessage()));
        }
    }
}
