package com.doctor.app.controller;

import com.doctor.app.model.*;
import com.doctor.app.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/addDoctor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {
        logger.info("Adding new doctor: {}", doctor.getName());
        return ResponseEntity.ok(adminService.addDoctor(doctor));
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        logger.info("Adding new user: {}", user.getUsername());
        return ResponseEntity.ok(adminService.addUser(user));
    }
}
