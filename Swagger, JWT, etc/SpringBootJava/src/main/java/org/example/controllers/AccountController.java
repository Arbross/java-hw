package org.example.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.example.dto.accountdto.LoginDTO;
import org.example.dto.accountdto.LoginSuccessDTO;

import org.example.dto.accountdto.RegisterDTO;
import org.example.dto.accountdto.RegisterSuccessDTO;
import org.example.interfaces.IAccountService;
import org.example.interfaces.IReCaptchaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/account")
@Api(tags = "Account")
public class AccountController {
    private final IAccountService accountService;
    private final IReCaptchaService reCaptchaService;

    @PostMapping("login")
    public ResponseEntity<LoginSuccessDTO> Login(@RequestBody @Valid LoginDTO data) {
        try {
            if (reCaptchaService.verify(data.getReCaptchaToken()) == false)
            {
                throw new IOException("ReCaptcha won't successfully validated.");
            }

            LoginSuccessDTO loginUser = accountService.Login(data.getEmail(), data.getPassword());

            return ResponseEntity.ok().body(loginUser);
        }
        catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("register")
    public ResponseEntity<RegisterSuccessDTO> Register(@RequestBody @Valid RegisterDTO data) {
        try {
            if (reCaptchaService.verify(data.getReCaptchaToken()) == false)
            {
                throw new IOException("ReCaptcha won't successfully validated.");
            }

            RegisterSuccessDTO registerUser = accountService.Register(data);

            return ResponseEntity.ok().body(registerUser);
        }
        catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
