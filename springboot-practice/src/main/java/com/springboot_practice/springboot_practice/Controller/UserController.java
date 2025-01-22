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
@RequestMapping({"/user"})
public class UserController {







    @Autowired
    private UserService userService;





    // endpoint for submit the answer of question  and get result

    @PostMapping({"/submit-answers"})
    public ResponseEntity<Result> submitAnswers(@RequestBody List<Response> responses) {
        Result result = this.userService.calculateResult(responses);
        return ResponseEntity.ok(result);
    }






}
