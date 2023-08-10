package com.application.recipeblog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.recipeblog.entity.UserDetails;
import com.application.recipeblog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

	public Map<String, Object> login(String username, String password) throws Exception {
        UserDetails user = userRepository.findByUsername(username);
        System.out.println(user.getId());
        if (user == null || !user.getPassword().equals(password)) {
            throw new Exception("Invalid username or password");
        }

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", user.getUsername());
        userInfo.put("id", user.getId());
        
        return userInfo;
    }


    public void register(UserDetails userDetails) throws Exception{
        if (userRepository.existsByUsername(userDetails.getUsername())) {
            throw new Exception("Username already exists");
        }

        userRepository.save(userDetails);
    }
}
