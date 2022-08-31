package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.example.entities.User;
import org.example.storage.IStorageService;
import org.example.classes.ImageUtils;
import org.example.storage.StorageProperties;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {
    private static List<User> users = new ArrayList<>();
    private final Path rootLocation;
    private final IStorageService storageService;

    public HomeController(StorageProperties properties, IStorageService storageService)
    {
        this.rootLocation = Paths.get(properties.getLocation());
        this.storageService = storageService;
    }

    @GetMapping("/user/")
    public List<User> get()
    {
        return users;
    }

    @PostMapping("/user/create")
    public String add(@RequestBody User user) {
        user.setImage(storageService.store(user.getImage()));
        users.add(user);
        return "Okay!";
    }

    @GetMapping("/user/{id}")
    public Optional<User> get(@PathVariable int id) {
        return users.stream().filter(x -> x.getId() == id).findFirst();
    }

    @PutMapping("/user/update")
    public String update(@RequestBody User user) {
        List<User> tmp = new ArrayList<>();
        for (User item : users) {
            if (item.getId() == user.getId())
            {
                user.setImage(storageService.store(user.getImage()));
                tmp.add(user);
                continue;
            }
            tmp.add(item);
        }

        users = tmp;

        return "Okay!";
    }

    @DeleteMapping("/user/delete/{id}")
    public String delete(@PathVariable int id) {
        for (int i = 0; i < users.stream().count(); i++)
        {
            User elem = users.get(i);

            if (elem.getId() == id)
            {
                users.remove(i);
            }
        }

        return "Okay!";
    }
}
