package com.springboot_practice.springboot_practice.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




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


    public QuestionDto() {
    }

    public QuestionDto(Long id, String questionText, String option1, String option2, String option3, String option4, String correctOption, Long departmentId) {
        this.id = id;
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.departmentId = departmentId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "Question text cannot be empty") String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(@NotEmpty(message = "Question text cannot be empty") String questionText) {
        this.questionText = questionText;
    }

    public @NotEmpty(message = "Option 1 cannot be empty") String getOption1() {
        return option1;
    }

    public void setOption1(@NotEmpty(message = "Option 1 cannot be empty") String option1) {
        this.option1 = option1;
    }

    public @NotEmpty(message = "Option 2 cannot be empty") String getOption2() {
        return option2;
    }

    public void setOption2(@NotEmpty(message = "Option 2 cannot be empty") String option2) {
        this.option2 = option2;
    }

    public @NotEmpty(message = "Option 3 cannot be empty") String getOption3() {
        return option3;
    }

    public void setOption3(@NotEmpty(message = "Option 3 cannot be empty") String option3) {
        this.option3 = option3;
    }

    public @NotEmpty(message = "Option 4 cannot be empty") String getOption4() {
        return option4;
    }

    public void setOption4(@NotEmpty(message = "Option 4 cannot be empty") String option4) {
        this.option4 = option4;
    }

    public @NotEmpty(message = "Correct option cannot be empty") String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(@NotEmpty(message = "Correct option cannot be empty") String correctOption) {
        this.correctOption = correctOption;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
