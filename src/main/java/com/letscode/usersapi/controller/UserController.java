package com.letscode.usersapi.controller;

import com.letscode.usersapi.domain.UserEntity;
import com.letscode.usersapi.domain.exchange.UserRequest;
import com.letscode.usersapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserRequest request) {
        UserEntity userToPersist = new UserEntity();
        userToPersist.setName(request.getName());
        userToPersist.setEmail(request.getEmail());

        UserEntity userPersisted = service.save(userToPersist);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userPersisted.getId()).toUri();

        return ResponseEntity.created(uri).body(userPersisted);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable String id) {
        UserEntity user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable String id, @RequestBody UserRequest request) {
        UserEntity userToUpdate = new UserEntity();
        userToUpdate.setName(request.getName());
        userToUpdate.setEmail(request.getEmail());

        return ResponseEntity.ok().body(service.update(id, userToUpdate));
    }
}
