package com.springboot_practice.springboot_practice.Repositorie;


import com.springboot_practice.springboot_practice.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {


    List<Question> findTop5ByDepartmentId(Long departmentId);
}
