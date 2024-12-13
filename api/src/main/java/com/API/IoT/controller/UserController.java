package com.API.IoT.controller;

import com.API.IoT.entity.UserEntity;
import com.API.IoT.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid UserEntity userEntity) {
        System.out.println("Meu usuários: " + userEntity);
        userService.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
        UserEntity user = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> user = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> findByEmail(@PathVariable String email) {
        UserEntity user = userService.findByEmail(email);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
