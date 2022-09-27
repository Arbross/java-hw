package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.entities.Role;
import org.example.repositories.RoleRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/role")
@Api(tags = "Role")
public class RoleController {
    private final RoleRepository roleRepository;
    public RoleController(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public List<Role> get()
    {
        return roleRepository.findAll();
    }

    @PostMapping("/")
    public String add(@Valid @RequestBody Role role) {
        roleRepository.save(role);

        return "Okay!";
    }

    @GetMapping("/{id}")
    public Optional<Role> get(@PathVariable int id) {
        return roleRepository.findById(id);
    }

    @PutMapping("/")
    public String update(@Valid @RequestBody Role role) {
        Role data = roleRepository.findById(role.getId()).get();

        data.setName(role.getName());

        roleRepository.save(role);

        return "Ok";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        Role data = roleRepository.findById(id).get();

        roleRepository.delete(data);

        return "Okay!";
    }
}
