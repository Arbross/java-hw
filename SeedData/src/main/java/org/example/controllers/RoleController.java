package org.example.controllers;

import org.example.classes.PassEncTech2;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.repositories.RoleRepository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {
    private final RoleRepository roleRepository;
    public RoleController(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/role/")
    public List<Role> get()
    {
        return roleRepository.findAll();
    }

    @PostMapping("/role/create")
    public String add(@Valid @RequestBody Role role) {
        roleRepository.save(role);

        return "Okay!";
    }

    @GetMapping("/role/{id}")
    public Optional<Role> get(@PathVariable int id) {
        return roleRepository.findById(id);
    }

    @PutMapping("/role/update")
    public String update(@Valid @RequestBody Role role) {
        Role data = roleRepository.findById(role.getId()).get();

        data.setName(role.getName());

        roleRepository.save(role);

        return "Ok";
    }

    @DeleteMapping("/role/delete/{id}")
    public String delete(@PathVariable int id) {
        Role data = roleRepository.findById(id).get();

        roleRepository.delete(data);

        return "Okay!";
    }
}
