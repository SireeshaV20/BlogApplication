package com.application.recipeblog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.application.recipeblog.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
    UserDetails findByUsername(String username); 
    boolean existsByUsername(String username);
}
