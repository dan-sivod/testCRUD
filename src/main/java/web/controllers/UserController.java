package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userServiceImp){
        this.userService = userServiceImp;
    }

    @GetMapping("/")
    public String printUsers(ModelMap model, Principal principal) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "user";
    }
}
