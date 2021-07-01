package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.UserService;

@Controller
@RequestMapping(value = "/")
public class RootController {
    private UserService userService;

    @Autowired
    RootController(UserService userServiceImp){
        this.userService = userServiceImp;
    }

    @GetMapping
    public String getHomePage(Model model) {
        return "redirect:/login";
    }

    @GetMapping(value = "login")
    public String getLoginPage() {
        return "login";
    }
}
