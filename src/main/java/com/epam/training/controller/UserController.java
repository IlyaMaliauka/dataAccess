package com.epam.training.controller;

import com.epam.training.model.impl.UserImpl;
import lombok.extern.slf4j.Slf4j;
import com.epam.training.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.epam.training.service.UserService;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("users/users");
        modelAndView.addObject("message", "All existing users: ");
        return modelAndView;
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") UserImpl user) {
        return "users/new";
    }

    @GetMapping("/delete")
    public String deleteUser(@ModelAttribute("user") UserImpl user) {
        return "users/delete";
    }

    @PostMapping()
    public ModelAndView createUser(@ModelAttribute("user") UserImpl user) {
        ModelAndView modelAndView = new ModelAndView("users/users");
        userService.createUser(user);

        modelAndView.addObject("users", user);
        modelAndView.addObject("message", String.format
                ("Successfully created new user with name %s and email %s. Created user id : %s", user.getName(), user.getEmail(), user.getId()));
        return modelAndView;
    }

    @PutMapping("/{id}")
    public ModelAndView updateUser(@PathVariable long id,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String email) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = userService.getUserById(id);
        if (Objects.nonNull(user)) {
            user.setName(name);
            user.setEmail(email);
            user = userService.updateUser(user);
            modelAndView.addObject("entities", user);
            modelAndView.addObject("message", "update entities");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @DeleteMapping()
    public ModelAndView deleteUserFromDatabase(@ModelAttribute("user") UserImpl userToDelete) {
        ModelAndView modelAndView = new ModelAndView("users/users");
        userService.deleteUser(userToDelete.getId());

        modelAndView.addObject("users", userToDelete);
        modelAndView.addObject("message", String.format
                ("Successfully deleted user with id %s", userToDelete.getId()));
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getUserById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = userService.getUserById(id);
        if (Objects.nonNull(user)) {
            modelAndView.addObject("entities", user);
            modelAndView.addObject("message", String.format("Successfully found a user with id %s", id));
        } else {
            modelAndView.addObject("message", String.format("Failed to find a user with id %s", id));
        }
        return modelAndView;
    }

    @GetMapping("/name/{name}")
    public ModelAndView getUsersByName(@PathVariable String name,
                                       @RequestParam(required = false, defaultValue = "25") int pageSize,
                                       @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<User> users = userService.getUsersByName(name, pageSize, pageNum);
        if (!users.isEmpty()) {
            modelAndView.addObject("entities", users);
            modelAndView.addObject("message", String.format("Successfully found a user with name %s", name));
        } else {
            modelAndView.addObject("message", String.format("Failed to find a user with name %s", name));
        }
        return modelAndView;
    }

    @GetMapping("/email/{email}")
    public ModelAndView getUsersByEmail(@PathVariable String email) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = userService.getUserByEmail(email);
        if (Objects.nonNull(user)) {
            modelAndView.addObject("entities", user);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }
}
