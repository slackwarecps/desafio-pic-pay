package br.com.fabiopereira.desafio1.controllers;

import br.com.fabiopereira.desafio1.dominio.user.User;
import br.com.fabiopereira.desafio1.dtos.UserDTO;
import br.com.fabiopereira.desafio1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
       List<User> users = this.userService.getAllUsers();
       return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
