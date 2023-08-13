package com.tiduswr.hruser.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tiduswr.hruser.entities.User;
import com.tiduswr.hruser.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserResource {
    
    @Autowired
    private UserRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<User> findById(@RequestParam String email){
        User user = repository.findByEmail(email).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(user);
    }

}
