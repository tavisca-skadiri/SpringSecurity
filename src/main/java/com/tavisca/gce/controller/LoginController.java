package com.tavisca.gce.controller;

import com.tavisca.gce.models.User;
import com.tavisca.gce.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class LoginController {
    @Autowired
    private LoginRepository repo;

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome to Saqlains Security Demo</h1>");
    }

    @GetMapping("/user")
    public String user(HttpServletRequest request) {
        String userName = request.getUserPrincipal().getName();
        return ("<h1>Welcome " + userName + " </h1>");
    }

    @GetMapping("/user/info")
    public ResponseEntity<User> userInfo(HttpServletRequest request) {
        String userName = request.getUserPrincipal().getName();
        Optional<User> user = repo.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @GetMapping("/admin")
    public String admin(HttpServletRequest request) {
        String userName = request.getUserPrincipal().getName();
        return ("<h1>Welcome Admin " + userName + " </h1>");
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
    }
}