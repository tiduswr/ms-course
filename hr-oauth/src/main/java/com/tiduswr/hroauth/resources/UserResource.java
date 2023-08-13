package com.tiduswr.hroauth.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiduswr.hroauth.entities.User;
import com.tiduswr.hroauth.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
    
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

}
