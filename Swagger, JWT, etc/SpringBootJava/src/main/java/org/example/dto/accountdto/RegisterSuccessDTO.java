package org.example.dto.accountdto;

import lombok.Data;

import java.util.List;

@Data
public class RegisterSuccessDTO {
    private String token;
    private String email;
    private String image;
    private String roles;
    private String id;
}
