package com.springboot_practice.springboot_practice.Repositorie;


import com.springboot_practice.springboot_practice.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
}