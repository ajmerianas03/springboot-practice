package com.springboot_practice.springboot_practice.Controller;

import com.springboot_practice.springboot_practice.DTO.QuestionDto;
import com.springboot_practice.springboot_practice.DTO.UserDto;
import com.springboot_practice.springboot_practice.Model.User;
import com.springboot_practice.springboot_practice.Response;
import com.springboot_practice.springboot_practice.Result;
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
    private UserService userService;

    @PostMapping({"/register"})
    public ResponseEntity<Object> registerUser(@RequestBody @Valid User user) {
        try {
            if (user.getUsername().contains(" ")) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid username", "message", "Username should not contain spaces"));
            } else {
                UserDto registeredUser = this.userService.registerUser(user.getUsername(), user.getPassword());
                return ResponseEntity.ok(registeredUser);
            }
        } catch (ResponseStatusException var4) {
            ResponseStatusException ex = var4;
            HttpStatus status = (HttpStatus)ex.getStatusCode();
            return ResponseEntity.status(status).body(Map.of("error", status.getReasonPhrase(), "message", ex.getMessage()));
        }
    }


    // endpoint for login and geting  question

    @PostMapping({"/login"})
    public ResponseEntity<List<QuestionDto>> loginAndGetQuestions(@RequestBody @Valid User user) {
        List<QuestionDto> questions = this.userService.loginAndGetQuestions(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(questions);
    }









}
