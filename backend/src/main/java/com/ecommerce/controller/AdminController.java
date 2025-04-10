package com.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"http://localhost:3000"})
public class AdminController {
    
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private static final String ADMIN_PASSWORD = "admin123"; // In a real app, this should be stored securely

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.info("Received admin login request");
        try {
            if (ADMIN_PASSWORD.equals(request.getPassword())) {
                logger.info("Admin login successful");
                return ResponseEntity.ok().build();
            } else {
                logger.warn("Invalid admin password attempt");
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            logger.error("Error during admin login: ", e);
            return ResponseEntity.status(500).build();
        }
    }

    // Inner class for login request
    private static class LoginRequest {
        private String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
} 