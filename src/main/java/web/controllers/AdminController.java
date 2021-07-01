package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    AdminController(UserService userServiceImp, RoleService roleService){
        this.userService = userServiceImp;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "new";
//    }
//
//    @PostMapping()
//    public String createUser(@ModelAttribute("User") @Valid User user, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()) {
//            return "new";
//        }
//        userService.createUser(user);
//        return "redirect:/";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String editUser(ModelMap model, @PathVariable("id") long id) {
//        model.addAttribute("user", userService.readUser(id));
//        return "edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String updateUser(@ModelAttribute("User") @Valid User user,
//                             BindingResult bindingResult,
//                             @PathVariable("id") long id) {
//        if(bindingResult.hasErrors()) {
//            return "edit";
//        }
//        userService.updateUser(id, user);
//        return "redirect:/";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable("id") long id) {
//        userService.deleteUser(id);
//        return "redirect:/";
//    }
}
