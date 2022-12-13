package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servise.RoleService;
import ru.kata.spring.boot_security.demo.servise.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllUsers(Principal principal, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("admin", userService.findUserByUsername(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("rolesAdd", roleService.getUniqAllRoles());
        return "admin";
    }

//    @GetMapping("/{id}")
//    public String showUser(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.getUser(id));
//        model.addAttribute("titleTable", "Страница пользователя:");
//        return "user";
//    }
//
//    @GetMapping("/addUser")
//    public String addNewUser(Model model, @ModelAttribute("user") User user) {
//        List<Role> roles = roleService.getUniqAllRoles();
//        model.addAttribute("rolesAdd", roles);
//        return "newUser";
//    }

    @PostMapping
    public String addCreateNewUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createNewUser(user);
        return "redirect:/admin";
    }

//    @GetMapping("/{id}/editUser")
//    public String edit(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.getUser(id));
//        List<Role> roles = roleService.getUniqAllRoles();
//        model.addAttribute("rolesAdd", roles);
//        return "edit";
//    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
