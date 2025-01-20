package com.springboot_practice.springboot_practice.Controller;

import com.springboot_practice.springboot_practice.DTO.DepartmentDto;
import com.springboot_practice.springboot_practice.DTO.QuestionDto;
import com.springboot_practice.springboot_practice.Exception.ResourceNotFoundException;
import com.springboot_practice.springboot_practice.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;



    @PostMapping("/department")
    public ResponseEntity<DepartmentDto> saveOrUpdateDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = adminService.saveOrUpdateDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }



    @DeleteMapping("/department/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        String response = adminService.deleteDepartment(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = adminService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping("/departments/{departmentId}/questions")
    public ResponseEntity<QuestionDto> addQuestionToDepartment(
            @PathVariable Long departmentId,
            @RequestBody QuestionDto questionDto) {

        try {

            QuestionDto addedQuestion = adminService.addQuestionsToDepartment(questionDto, departmentId);
            return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @DeleteMapping("/questions/{q_Id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long q_Id) {
        try {

            String message = adminService.deleteQuestion(q_Id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
    }


}
