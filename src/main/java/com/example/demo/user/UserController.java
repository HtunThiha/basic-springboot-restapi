package com.example.demo.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsersList = userRepository.findAll();

        if (allUsersList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(allUsersList);
    }

    @GetMapping
    public ResponseEntity<Optional<User>> getUserById(@RequestParam Integer id) {
        Optional<User> retrievedUser = userRepository.findById(id);

        if (retrievedUser.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(retrievedUser);
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@Valid @RequestBody User user) {
        User newUser = userRepository.save(user);
        return ResponseEntity.status(201).body(newUser);
    }

}
