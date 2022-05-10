package com.emerald.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private UUID id;
    @NotBlank
    @Pattern(regexp = "^[a-z ,.'-]+$")
    private String name;

    @NotNull
    @Min(0)
    @Max(150)
    private Integer age;
}
