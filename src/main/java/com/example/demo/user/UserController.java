package com.example.demo.user;

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
    public ResponseEntity getAllUsers() {
        List<User> allUsersList = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allUsersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {
        Optional<User> retrievedUser = userRepository.findById(id);

        if (retrievedUser.isEmpty()) {
            Map<String, String> errorMsg = new HashMap<>();
            errorMsg.put("message", "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(retrievedUser);
        }
    }

    @PostMapping
    public ResponseEntity createNewUser(@RequestBody User user) {
        String reqUsername = user.getUsername();
        Optional<User> userWithSameId = userRepository.findByUsername(reqUsername);

        if (!userWithSameId.isEmpty()) {
            Map<String, String> errorMsg = new HashMap<>();
            errorMsg.put("message", "Username already exists.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
        } else {
            User savedUser = userRepository.save(user);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "User created successfully.");
            response.put("user", savedUser);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

}
