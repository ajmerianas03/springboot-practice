package com.springboot_practice.springboot_practice.Service;

import com.springboot_practice.springboot_practice.DTO.QuestionDto;
import com.springboot_practice.springboot_practice.DTO.UserDto;
import com.springboot_practice.springboot_practice.Response;
import com.springboot_practice.springboot_practice.Result;

import java.util.List;

public interface UserService {

    List<QuestionDto> loginAndGetQuestions(String username, String password);

    Result calculateResult(List<Response> responses);

    UserDto registerUser(String username, String password);
}
