package com.springboot_practice.springboot_practice.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class QuestionDto {

    private Long id;

    @NotEmpty(message = "Question text cannot be empty")
    private String questionText;

    @NotEmpty(message = "Option 1 cannot be empty")
    private String option1;

    @NotEmpty(message = "Option 2 cannot be empty")
    private String option2;

    @NotEmpty(message = "Option 3 cannot be empty")
    private String option3;

    @NotEmpty(message = "Option 4 cannot be empty")
    private String option4;

    @NotEmpty(message = "Correct option cannot be empty")
    private String correctOption;

    private Long departmentId; // Reference to Department entity by ID

}
