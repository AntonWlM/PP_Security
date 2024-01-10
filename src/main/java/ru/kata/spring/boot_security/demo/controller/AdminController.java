package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.models.User;

import java.security.Principal;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", userService.list());
        return "admin/list";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
      //  model.addAttribute("roles", roleService.listRoles());
        return "admin/new_user";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){
       // user.setRoles(roleService.findRoleById(roleId));
        userService.save(user);
        return "redirect:/admin/";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam(value = "id") Long id,
                           Model model) {
        model.addAttribute("user", userService.find(id));
        return "admin/edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("id") Long id) {//, @RequestParam("roles") Long roleId) {
       // user.setRoles(roleService.findRoleById(roleId));
        userService.update(user);
        return "redirect:/admin/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }
    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        return "user/user";
    }
}


