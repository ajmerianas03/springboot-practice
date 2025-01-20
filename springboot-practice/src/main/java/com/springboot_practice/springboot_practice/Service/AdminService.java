package com.springboot_practice.springboot_practice.Service;

import com.springboot_practice.springboot_practice.DTO.DepartmentDto;
import com.springboot_practice.springboot_practice.DTO.QuestionDto;
import com.springboot_practice.springboot_practice.Model.Department;

import java.util.List;

public interface AdminService {


    //for add or update department

    public DepartmentDto  saveOrUpdateDepartment(DepartmentDto departmentDto);


    // for delete department by id
    public String deleteDepartment(Long departmentId);



    // for get all department
    public List<DepartmentDto> getAllDepartments();


    //for save question by department

    public QuestionDto addQuestionsToDepartment(QuestionDto questionsDto, Long departmentId);

    public String deleteQuestion(Long q_Id);



}
