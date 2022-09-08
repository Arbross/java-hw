package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.classes.PassEncTech2;
import org.example.constants.Roles;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.interfaces.IDatabaseSeed;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
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
        if (this.roleRepository.count() == 0)
        {
            this.roleRepository.save(new Role(Roles.Admin));
            this.roleRepository.save(new Role(Roles.User));
        }

        if (this.userRepository.count() == 0)
        {
            User user = new User();
            user.setEmail("semen@gmail.com");
            user.setPhoneNumber("096-620-7377");
            user.setPassword(PassEncTech2.toHexString("123456".getBytes(StandardCharsets.UTF_8)));
            user.setRoles(Arrays.asList(
                    roleRepository.findByName(Roles.Admin)));
            this.userRepository.save(user);
        }
    }
}
