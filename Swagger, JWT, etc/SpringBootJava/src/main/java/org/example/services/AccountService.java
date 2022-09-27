package org.example.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.example.classes.ImageUtils;
import org.example.dto.accountdto.RegisterDTO;
import org.example.interfaces.IFileWorkingService;
import org.example.configurations.security.JwtTokenUtil;
import org.example.dto.accountdto.LoginSuccessDTO;
import org.example.dto.accountdto.RegisterSuccessDTO;
import org.example.entities.User;
import org.example.interfaces.IAccountService;
import org.example.repositories.UserRepository;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final IFileWorkingService fileService;

    @Override
    public LoginSuccessDTO Login(String email, String password) throws BadCredentialsException {
        User dbUser = userRepository.findByEmail(email);
        if (dbUser == null)
        {
            throw new BadCredentialsException("Unauthorized!");
        }

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(password);

        if (passwordEncryptor.checkPassword(dbUser.getPassword(), encryptedPassword))
        {
            throw new BadCredentialsException("The password you entered in is wrong.");
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        LoginSuccessDTO response = new LoginSuccessDTO();
        response.setEmail(dbUser.getEmail());
        response.setToken(jwtTokenUtil.generateAccessToken(dbUser));
        response.setRoles(gson.toJson(dbUser.getRoles()));
        response.setImage("http://localhost:8080/api/file/" + ImageUtils.readImages(dbUser.getImage())[0]);
        response.setId(String.valueOf(dbUser.getId()));

        return response;
    }

    @Override
    public RegisterSuccessDTO Register(RegisterDTO data) throws BadCredentialsException {
        User user = userRepository.findByEmail(data.getEmail());
        if (user != null)
        {
            throw new BadCredentialsException("There is no user with email you entered in.");
        }

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(data.getPassword());

        User tmp = new User();
        try
        {
            tmp.setEmail(data.getEmail());
            tmp.setPassword(encryptedPassword);
            tmp.setAge(data.getAge());
            tmp.setPhoneNumber(data.getPhoneNumber());
            String image = fileService.uploadFile(data.getImage());
            tmp.setImage(image);

            userRepository.save(tmp);
        }
        catch (BadCredentialsException ex) {
            throw new BadCredentialsException(ex.getMessage());
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        RegisterSuccessDTO response = new RegisterSuccessDTO();
        response.setEmail(tmp.getEmail());
        response.setToken(jwtTokenUtil.generateAccessToken(tmp));
        response.setRoles(gson.toJson(tmp.getRoles()));
        response.setImage("https://localhost:8080/api/files/" + tmp.getImage());
        response.setId(String.valueOf(tmp.getId()));

        return response;
    }
}
