package org.example.controllers;

import org.example.classes.PassEncTech2;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.example.storage.IStorageService;
import org.example.storage.StorageProperties;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {
    private final UserRepository userRepository;
    private final Path rootLocation;
    private final IStorageService storageService;

    public HomeController(StorageProperties properties, IStorageService storageService, UserRepository userRepository)
    {
        this.rootLocation = Paths.get(properties.getLocation());
        this.storageService = storageService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/")
    public List<User> get()
    {
        return userRepository.findAll();
    }

    @PostMapping("/user/create")
    public String add(@Valid @RequestBody User user, Errors errors) {
        user.setImage(storageService.store(user.getImage()));
        user.setPassword(PassEncTech2.toHexString(user.getPassword().getBytes(StandardCharsets.UTF_8)));

        userRepository.save(user);

        return "Okay!";
    }

    @GetMapping("/user/{id}")
    public Optional<User> get(@PathVariable int id) {
        return userRepository.findById(id);
    }

    @PutMapping("/user/update")
    public String update(@Valid @RequestBody User user) {
        User data = userRepository.findById(user.getId()).get();

        data.setEmail(user.getEmail());
        data.setImage(user.getImage());
        data.setAge(user.getAge());
        data.setPhoneNumber(user.getPhoneNumber());
        data.setPassword(user.getPassword());

        userRepository.save(user);

        return "Ok";
    }

    @DeleteMapping("/user/delete/{id}")
    public String delete(@PathVariable int id) {

        User data = userRepository.findById(id).get();

        storageService.removeFiles(data.getImage());

        userRepository.delete(data);

        return "Okay!";
    }
}
