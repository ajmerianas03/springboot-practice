package com.springboot_practice.springboot_practice.ServiceImpl;

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
    public List<QuestionDto> loginAndGetQuestions(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getPassword().equals(password)) {

                List<Question> questions = questionRepository.findTop5ByDepartmentId(user.getDepartment().getId());

                List<QuestionDto> questionDtos = new ArrayList<>();

                for (Question q : questions) {
                    QuestionDto questionDto = modelMapper.map(q, QuestionDto.class);
                    questionDtos.add(questionDto);
                }

                return questionDtos;
            }
        }

        throw new RuntimeException("Invalid credentials");
    }

    @Override
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
    }

    @Override
    public UserDto registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with username '" + username + "' already exists.");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        User savedUser = userRepository.save(newUser);

        return modelMapper.map(savedUser, UserDto.class); // Use ModelMapper to convert User to UserDto
    }
}
