package com.example.ttok0801joinusexample;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public String mainPage(){
        System.out.println("-----------mainpageindex-------");
        return "index";
    }

    @PostMapping("/user")
    public String userAdd(User user){
        System.out.println("------유저는-----");
        System.out.println(user);
        userDao.insert(user);
        return "--------userAdd-------------";
    }

    @GetMapping("/user")
    public String userListPage(Model model){
        model.addAttribute("users",userDao.listForBeanPropertyRowMapper());
        return "users";
    }


}
