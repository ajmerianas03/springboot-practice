package com.springboot_practice.springboot_practice.ServiceImpl;

import com.springboot_practice.springboot_practice.DTO.QuestionDto;
import com.springboot_practice.springboot_practice.Model.Question;
import com.springboot_practice.springboot_practice.Model.User;
import com.springboot_practice.springboot_practice.Repositorie.QuestionRepository;
import com.springboot_practice.springboot_practice.Repositorie.UserRepository;
import com.springboot_practice.springboot_practice.Service.UserLoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private JWTServiceImpl jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository repo;


    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

/*    public String verify(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername())  ;
        } else {
            return "fail";
        }
    }*/

    @Override
    public Map<String, Object> verify(User user) {
        try {

            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if (authentication.isAuthenticated()) {

                String token = jwtService.generateToken(user.getUsername());

                // Fetch user details from the repository
                User authenticatedUser = repo.findByUsername(user.getUsername())
                        .orElseThrow(() -> new RuntimeException("User not found"));


                List<Question> questions = questionRepository.findTop5ByDepartmentId(authenticatedUser.getDepartment().getId());
                List<QuestionDto> questionDtos = new ArrayList<>();

                for (Question question : questions) {
                    QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
                    questionDtos.add(questionDto);
                }


                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("token", token);
                response.put("questions", questionDtos);

                return response;
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid username or password", e);
        }

        throw new RuntimeException("Authentication failed");
    }
}
