package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {

    @GetMapping(value = "/")
    public String getHomePage(Model model) {
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

}
