package com.application.recipeblog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.recipeblog.dto.CommentDTO;
import com.application.recipeblog.dto.UserCommentDTO;
import com.application.recipeblog.entity.Comment;
import com.application.recipeblog.entity.Recipe;
import com.application.recipeblog.entity.UserDetails;
import com.application.recipeblog.repository.CommentRepository;
import com.application.recipeblog.repository.RecipeRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private RecipeRepository recipeRepository;


	public List<CommentDTO> getAllCommentsForRecipe(Long recipeId) {
	    List<Comment> comments = commentRepository.findCommentsByRecipeId(recipeId);
	    return comments.stream()
	            .map(this::convertCommentToDTO)
	            .collect(Collectors.toList());
	}

	private CommentDTO convertCommentToDTO(Comment comment) {
	    CommentDTO commentDTO = new CommentDTO();
	    commentDTO.setId(comment.getId());
	    commentDTO.setText(comment.getText());

	    UserCommentDTO userCommentDTO = new UserCommentDTO();
	    userCommentDTO.setId(comment.getUserDetails().getId());
	    userCommentDTO.setUsername(comment.getUserDetails().getUsername());

	    commentDTO.setUserDetails(userCommentDTO);
	    return commentDTO;
	}
	
	 public CommentDTO saveComment(Long recipeId, CommentDTO commentDTO, Long userId) throws Exception {
	        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
	        if (optionalRecipe.isPresent()) {
	            Recipe recipe = optionalRecipe.get();

	            Comment comment = new Comment();
	            comment.setText(commentDTO.getText());
	            comment.setRecipe(recipe);

	            UserDetails userDetails = new UserDetails();
	            userDetails.setId(userId);
	            comment.setUserDetails(userDetails);

	            Comment savedComment = commentRepository.save(comment);
	            return mapCommentToDTO(savedComment);
	        } else {
	            throw new Exception("Recipe not found with id: " + recipeId);
	        }
	    }

	    private CommentDTO mapCommentToDTO(Comment comment) {
	        CommentDTO dto = new CommentDTO();
	        dto.setId(comment.getId());
	        dto.setText(comment.getText());

	        UserCommentDTO userCommentDTO = new UserCommentDTO();
	        userCommentDTO.setId(comment.getUserDetails().getId());
	        userCommentDTO.setUsername(comment.getUserDetails().getUsername());
	        dto.setUserDetails(userCommentDTO);

	        return dto;
	    }
	}








	    




	
	   