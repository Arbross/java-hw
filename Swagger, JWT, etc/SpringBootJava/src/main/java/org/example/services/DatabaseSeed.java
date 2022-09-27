package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.constants.Roles;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.interfaces.IDatabaseSeed;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DatabaseSeed implements IDatabaseSeed {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void Seed() {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword("123456");

        if (this.roleRepository.count() == 0)
        {
            this.roleRepository.save(new Role(Roles.Admin));
            this.roleRepository.save(new Role(Roles.User));
        }

        if (this.userRepository.count() == 0)
        {
//            User user = new User();
//            user.setEmail("semen@gmail.com");
//            user.setPhoneNumber("096-620-7377");
//            user.setPassword(encryptedPassword);
//            user.setRoles(Arrays.asList(
//                    roleRepository.findByName(Roles.Admin)));
//            this.userRepository.save(user);
        }
    }
}
