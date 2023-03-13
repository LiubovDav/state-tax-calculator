package org.liubov.statetaxcalculator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Integer id;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotEmpty(message = "Confirm Password should not be empty")
    private String password2;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
