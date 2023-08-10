package com.application.recipeblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.recipeblog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
    List<Comment> findByRecipeId(Long recipeId);
    
    @Query("SELECT c FROM Comment c JOIN FETCH c.userDetails WHERE c.recipe.id = :recipeId")
    List<Comment> findCommentsByRecipeId(@Param("recipeId") Long recipeId);
    

}
