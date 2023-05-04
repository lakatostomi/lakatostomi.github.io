package com.example.caloriecalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Jacksonized
public class IntakeDTO {

    @NotNull(message = "The field can not be null!")
    @Positive(message = "The value of the field has to be positive!")
    private Integer foodId;
    @NotNull(message = "The field can not be null!")
    @Positive(message = "The value of the field has to be positive!")
    private Integer userId;
    @NotNull(message = "The field can not be null!")
    @Positive(message = "The value of the field has to be positive!")
    private Double quantityOfFood;
}
