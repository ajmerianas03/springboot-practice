package com.springboot_practice.springboot_practice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DepartmentDto {

    private Long id;

    @NotBlank(message = "Department name cannot be blank.")
    @Size(min = 2, max = 50, message = "Department name must be between 2 and 50 characters.")
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Department name cannot be blank.") @Size(min = 2, max = 50, message = "Department name must be between 2 and 50 characters.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Department name cannot be blank.") @Size(min = 2, max = 50, message = "Department name must be between 2 and 50 characters.") String name) {
        this.name = name;
    }
}
