package com.example.rewinder.controller;

import com.example.rewinder.model.User;
import com.example.rewinder.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    // Constructor injection
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        // Set CSV content type and file download headers
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"users.csv\"");

        // Write CSV data
        PrintWriter writer = response.getWriter();
        writer.println("ID,Name,Email"); // CSV Header

        List<User> users = userRepository.findAll();
        for (User user : users) {
            writer.println(user.getId() + "," + user.getName() + "," + user.getEmail());
        }
    }
}
