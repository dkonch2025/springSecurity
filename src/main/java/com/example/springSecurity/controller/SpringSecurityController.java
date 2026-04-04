package com.example.springSecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springSecurity.dto.Users;
import com.example.springSecurity.service.UserService;
import com.example.springSecurity.util.JwtUtil;

@RestController
public class SpringSecurityController {


    @Autowired
    UserService userService;

    @GetMapping("/message")
    public String getMessage(){
     return "Hello. Working on spring security";
    }

    @PostMapping("/generateToken")
    public String login(@RequestParam String username) {

        String token = JwtUtil.generateToken(username);

        return token;
    }

    @GetMapping("/validateToken")
    public String validate(@RequestParam String token) {

        boolean valid = JwtUtil.validateToken(token);

        if(valid) {
            return "Token is valid. Username: " + JwtUtil.getUsername(token);
        } else {
            return "Invalid Token";
        }
    }

    @GetMapping("/getUserList")
    public List<Users> getUserList(){
       
        return userService.findUser();
    }

     @GetMapping("/searchUser/{id}")
    public Users getUserList(@PathVariable String id){
       
        return userService.searchUser(id);
    }
}
