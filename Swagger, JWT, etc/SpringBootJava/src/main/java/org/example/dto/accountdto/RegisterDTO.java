package org.example.dto.accountdto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegisterDTO {
    @Email
    @Size(min=8, max=50)
    @ApiModelProperty(notes = "User E-Mail", example = "test@test.com", required = true)
    @NotBlank(message = "Email can't be empty.")
    private String email;
    @ApiModelProperty(notes = "User Image", example = "*image*", required = true)
    private String image;
    @Size(min=8, max=50)
    @NotBlank(message = "Password can't be empty.")
    @ApiModelProperty(notes = "User Password", example = "12345678", required = true)
    private String password;
    @ApiModelProperty(notes = "User Phone Number", example = "380654568745", required = true)
    @NotBlank(message = "Phone Number can't be empty.")
    private String phoneNumber;
    @Min(18)
    @ApiModelProperty(notes = "User Age", example = "18", required = true)
    private int age;
    @ApiModelProperty(notes = "ReCaptcha Token", example = "*token*", required = true)
    @NotNull(message = "Please enter reCaptchaToken, it can't be null.")
    private String reCaptchaToken;
}
