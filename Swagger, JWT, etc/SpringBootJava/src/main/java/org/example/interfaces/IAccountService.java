package org.example.interfaces;

import org.example.dto.accountdto.LoginSuccessDTO;
import org.example.dto.accountdto.RegisterDTO;
import org.example.dto.accountdto.RegisterSuccessDTO;
import org.springframework.security.authentication.BadCredentialsException;

public interface IAccountService {
    LoginSuccessDTO Login(String email, String password) throws BadCredentialsException;
    RegisterSuccessDTO Register(RegisterDTO data) throws BadCredentialsException;
}
