package com.springboot_practice.springboot_practice.Service;

import com.springboot_practice.springboot_practice.DTO.QuestionDto;
import com.springboot_practice.springboot_practice.DTO.UserDto;
import com.springboot_practice.springboot_practice.Response;
import com.springboot_practice.springboot_practice.Result;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {


    Result calculateResult(List<Response> responses);




}
