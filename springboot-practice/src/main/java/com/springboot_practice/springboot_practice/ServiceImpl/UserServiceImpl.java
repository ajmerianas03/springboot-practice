package com.springboot_practice.springboot_practice.ServiceImpl;

import com.springboot_practice.springboot_practice.Exception.QuestionNotFoundException;
import com.springboot_practice.springboot_practice.Model.Question;
import com.springboot_practice.springboot_practice.Model.User;
import com.springboot_practice.springboot_practice.Repositorie.QuestionRepository;
import com.springboot_practice.springboot_practice.Repositorie.UserRepository;
import com.springboot_practice.springboot_practice.Service.UserService;

import org.modelmapper.ModelMapper;
import com.springboot_practice.springboot_practice.DTO.QuestionDto;
import com.springboot_practice.springboot_practice.DTO.UserDto;
import com.springboot_practice.springboot_practice.Response;
import com.springboot_practice.springboot_practice.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Result calculateResult(List<Response> responses) {
        int correct = 0;
        int wrong = 0;

        for (Response response : responses) {
            Question question = questionRepository.findById(response.getqId())
                    .orElseThrow(() -> new QuestionNotFoundException("The Question ID " + response.getqId() + " does not exist."));

            if (question.getCorrectOption().equalsIgnoreCase(response.getUseranswer())) {
                correct++;
            } else {
                wrong++;
            }
        }

        int total = correct + wrong;
        double percentage = 0;
        if (total > 0) {
            percentage = ((double) correct / total) * 100;
        }

        return new Result(correct, wrong, percentage);
    }


    /*@Override
    public Result calculateResult(List<Response> responses) {
        int correct = 0;
        int wrong = 0;

        for (Response response : responses) {

            Question question = questionRepository.findById(response.getqId())
                    .orElseThrow(() -> new IllegalArgumentException("Question not found"));
            if (question.getCorrectOption().equalsIgnoreCase(response.getUseranswer())) {
                correct++;
            } else {
                wrong++;
            }
        }

        int total = correct + wrong;
        double percentage = 0;
        if (total > 0) {
            percentage = ((double) correct / total) * 100;
        }

        return new Result(correct, wrong, percentage);
    }*/



}
