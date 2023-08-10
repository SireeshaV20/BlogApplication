package com.application.recipeblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.recipeblog.dto.UserDetailsDTO;
import com.application.recipeblog.entity.UserDetails;
import com.application.recipeblog.repository.UserRepository;
import com.application.recipeblog.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private UserRepository userRepository;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
	    String username = request.get("username");
	    String password = request.get("password");

	    try {
	        Map<String, Object> loggedInUserInfo = userService.login(username, password);
	        if (loggedInUserInfo != null) {
	            return ResponseEntity.ok(loggedInUserInfo);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}
	
	@GetMapping("/check-username/{username}")
    public ResponseEntity<?> checkUsernameAvailability(@PathVariable String username) {
        boolean isUsernameAvailable = !userRepository.existsByUsername(username);
        return ResponseEntity.ok(isUsernameAvailable);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(userDetailsDTO.getEmail());
        userDetails.setUsername(userDetailsDTO.getUsername());
        userDetails.setPassword(userDetailsDTO.getPassword());

        try {
            userService.register(userDetails);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
