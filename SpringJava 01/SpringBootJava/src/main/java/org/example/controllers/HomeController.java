package org.example.controllers;

import org.example.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {
    private static List<User> users = new ArrayList<>();

    @GetMapping("/user/")
    public List<User> get()
    {
        return users;
    }

    @PostMapping("/user/create")
    public String add(@RequestBody User user) {
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
