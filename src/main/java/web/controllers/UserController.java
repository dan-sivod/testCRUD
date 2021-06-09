package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    UserService userService;

    UserController( UserServiceImp userServiceImp){
        this.userService = userServiceImp;
    }

    @GetMapping(value ="/")
    public String printUsers(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);

        User user = new User("1","1",1);
        user.setId(1L);
        userService.createUser(user);
        System.out.println(userService.readUser(1));
        return "index";
    }

//    @GetMapping(values ="/{id}")
//    public String showUser(@PathVariable("id") long id, ModelMap model) {
//        model.addAttribute("id", userService.readUser(id));
//        return "user1";
//    }

//    @PostMapping(values ="/new")
//    public String showUser(@RequestParam("name") String name, @RequestParam("lastName") String lastName,
//                           @RequestParam("age") int age, ModelMap model) {
//        User user = new User(name,lastName,age);
//        model.addAttribute("user", user);
//        return "new";
//    }

//    @GetMapping("/new")
//    public String newUser(ModelMap model) {
//        model.addAttribute("user", new User());
//        return "new";
//    }
}
