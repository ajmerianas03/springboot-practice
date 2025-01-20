package com.springboot_practice.springboot_practice.Repositorie;


import com.springboot_practice.springboot_practice.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
