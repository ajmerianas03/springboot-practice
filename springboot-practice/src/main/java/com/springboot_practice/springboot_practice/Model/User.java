package com.springboot_practice.springboot_practice.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(regexp = "^[^\\s]*$", message = "Username cannot contain spaces")
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "Username cannot be empty") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Username cannot be empty") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "Password cannot be empty") @Size(min = 6, message = "Password must be at least 6 characters") @Pattern(regexp = "^[^\\s]*$", message = "Username cannot contain spaces") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password cannot be empty") @Size(min = 6, message = "Password must be at least 6 characters") @Pattern(regexp = "^[^\\s]*$", message = "Username cannot contain spaces") String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
