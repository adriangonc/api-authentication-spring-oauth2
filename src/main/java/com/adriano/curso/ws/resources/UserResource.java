package com.adriano.curso.ws.resources;

import com.adriano.curso.ws.domain.User;
import com.adriano.curso.ws.dto.UserDTO;
import com.adriano.curso.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> listUserDTO = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        System.out.println("Lista da entidade User convertida para UserDTO");
        return ResponseEntity.ok().body(listUserDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

}
