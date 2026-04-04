package com.example.springSecurity.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.example.springSecurity.dto.Users;
import com.example.springSecurity.exception.RecordNotFoundException;

@Service
public class UserService {

    public static List<Users> users = new ArrayList<>();
    static{
        users.add(new Users("1","abc",LocalDate.now().minusYears(45)));
        users.add(new Users("2","devvan",LocalDate.now().minusYears(30)));
        users.add(new Users("3","hhrha",LocalDate.now().minusYears(30)));
    }

    public List<Users> findUser(){

        return users;
    }

    public Users searchUser(String id){

        return users.stream().filter(usr->usr.getId().equals(id)).findFirst()
        .orElseThrow(()->new RecordNotFoundException("User Not Found with Id:"+id));
    }
}
