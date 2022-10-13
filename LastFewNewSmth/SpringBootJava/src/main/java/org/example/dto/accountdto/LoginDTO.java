package org.example.dto.accountdto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {
    @Email(message = "Email isn't entered correctly.")
    @NotNull(message = "Please enter email, it can't be null.")
    @ApiModelProperty(notes = "Login User Email", example = "test@test.com", required = true)
    private String email;
    @ApiModelProperty(notes = "Login User Password", example = "12345678", required = true)
    @NotNull(message = "Please enter password, it can't be null.")
    private String password;
    @ApiModelProperty(notes = "ReCaptcha Token", example = "*token*", required = true)
    @NotNull(message = "Please enter reCaptchaToken, it can't be null.")
    private String reCaptchaToken;
}
