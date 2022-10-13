package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.classes.ImageUtils;
import org.example.dto.userdto.CreateUserItemDTO;
import org.example.dto.userdto.UserItemDTO;
import org.example.entities.User;
import org.example.mapper.ApplicationMapper;
import org.example.repositories.UserRepository;
import org.example.storage.IStorageService;
import org.example.storage.StorageProperties;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/user")
@Api(tags = "User")
public class UserController {
    private final UserRepository userRepository;
    private final Path rootLocation;
    private final IStorageService storageService;
    private final ApplicationMapper mapper;

    public UserController(StorageProperties properties, IStorageService storageService, UserRepository userRepository, ApplicationMapper mapper)
    {
        this.rootLocation = Paths.get(properties.getLocation());
        this.storageService = storageService;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public List<UserItemDTO> get()
    {
        List<User> tmp = new ArrayList<>();
        for (User user : userRepository.findAll())
        {
            user.setImage("http://localhost:8080/api/file/" + ImageUtils.readImages(user.getImage())[0]);
            tmp.add(user);
        }

        return mapper.usersToUserItemDTO_List(tmp);
    }

    @PostMapping("/")
    public String add(@Valid @RequestBody CreateUserItemDTO user) {
        try {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            user.setImage(storageService.store(user.getImage()));
            user.setPassword(encoder.encode(user.getPassword()));

            userRepository.save(mapper.userItemDTOToUser(user));

            return "Okay!";
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        User user = userRepository.findById(id).get();
        user.setImage("http://localhost:8080/api/file/" + ImageUtils.readImages(user.getImage())[0]);

        return user;
    }

    @PutMapping("/")
    public String update(@Valid @RequestBody UserItemDTO user) {
        User data = userRepository.findById(user.getId()).get();

        data.setEmail(user.getEmail());
        data.setImage(storageService.store(user.getImage()));
        data.setAge(user.getAge());
        data.setPhoneNumber(user.getPhoneNumber());
        data.setPassword(user.getPassword());

        userRepository.save(data);

        return "Ok";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {

        User data = userRepository.findById(id).get();

        storageService.removeFiles(data.getImage());

        userRepository.delete(data);

        return "Okay!";
    }
}
