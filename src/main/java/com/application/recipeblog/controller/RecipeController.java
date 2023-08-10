package com.application.recipeblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.recipeblog.dto.CommentDTO;
import com.application.recipeblog.dto.RecipeDTO;
import com.application.recipeblog.service.CommentService;
import com.application.recipeblog.service.RecipeService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
	
	@Autowired
    private RecipeService recipeService;
	
	@Autowired
    private CommentService commentService;

    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public RecipeDTO saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        return recipeService.saveRecipe(recipeDTO);
    }

    @GetMapping("/{recipeId}/comments")
    public List<CommentDTO> getAllCommentsForRecipe(@PathVariable Long recipeId) {
        return commentService.getAllCommentsForRecipe(recipeId);
    }

    @PostMapping("/{recipeId}/comments")
    public ResponseEntity<CommentDTO> saveComment(
            @PathVariable Long recipeId,
            @RequestBody CommentDTO commentDTO
    ) throws Exception {
        Long userId = commentDTO.getUserDetails().getId(); 
        CommentDTO savedComment = commentService.saveComment(recipeId, commentDTO, userId);
        return ResponseEntity.ok(savedComment);
    }

}
