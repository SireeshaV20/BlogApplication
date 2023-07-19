package com.application.BlogApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.BlogApplication.Entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}

