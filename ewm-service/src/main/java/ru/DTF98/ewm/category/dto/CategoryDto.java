package ru.DTF98.ewm.category.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class CategoryDto {
    @Null
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
}
