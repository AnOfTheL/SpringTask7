package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private User user;

    @GetMapping()
    public String index(ModelMap model){
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String create(@RequestParam("name") String name,
                         @RequestParam("lastname") String lastname,
                         @RequestParam("age") byte age)
    {
        user = new User(name, lastname, age);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@RequestParam("name") String name,
                         @RequestParam("lastname") String lastname,
                         @RequestParam("age") byte age,
                         @PathVariable("id") long id) {
        userService.updateUser(id, name, lastname, age);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
