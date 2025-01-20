package com.springboot_practice.springboot_practice.ServiceImpl;

import com.springboot_practice.springboot_practice.DTO.DepartmentDto;
import com.springboot_practice.springboot_practice.DTO.QuestionDto;
import com.springboot_practice.springboot_practice.Exception.ResourceAlreadyExistsException;
import com.springboot_practice.springboot_practice.Exception.ResourceNotFoundException;
import com.springboot_practice.springboot_practice.Model.Department;
import com.springboot_practice.springboot_practice.Model.Question;
import com.springboot_practice.springboot_practice.Model.User;
import com.springboot_practice.springboot_practice.Repositorie.DepartmentRepository;
import com.springboot_practice.springboot_practice.Repositorie.QuestionRepository;
import com.springboot_practice.springboot_practice.Service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveOrUpdateDepartment(DepartmentDto departmentDto) {
        if (departmentRepository.existsByName(departmentDto.getName())) {
            throw new ResourceAlreadyExistsException("Department", "name", departmentDto.getName());
        }


        if (departmentDto.getId() != null && departmentRepository.existsById(departmentDto.getId())) {
            Department department = departmentRepository.findById(departmentDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentDto.getId()));
            department.setName(departmentDto.getName());
            departmentRepository.save(department);
            return departmentDto;
        } else {

            Department newDepartment = new Department();
            newDepartment.setName(departmentDto.getName());
            departmentRepository.save(newDepartment);
            departmentDto.setId(newDepartment.getId());
            return departmentDto;
        }
    }

    @Override
    public String deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));

        departmentRepository.delete(department);


        return "Department deleted successfully";


    }

    @Override
    public List<DepartmentDto> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();


        List<DepartmentDto> departmentDtos = departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class)) // Use ModelMapper or manually map
                .collect(Collectors.toList());

        return departmentDtos;
    }

    @Override
    public QuestionDto addQuestionsToDepartment(QuestionDto questionDto, Long departmentId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));


        Question question = modelMapper.map(questionDto, Question.class);


        question.setDepartment(department);


        questionRepository.save(question);


        return modelMapper.map(question, QuestionDto.class);
    }

    @Override
    public String deleteQuestion(Long q_Id) {

        Question question = questionRepository.findById(q_Id)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", q_Id));


        questionRepository.delete(question);

        return "Question deleted successfully";
    }
}
