package com.application.recipeblog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.recipeblog.dto.RecipeDTO;
import com.application.recipeblog.entity.Recipe;
import com.application.recipeblog.repository.RecipeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;

 public List<RecipeDTO> getAllRecipes() {
     List<Recipe> recipes = recipeRepository.findAll();
     return recipes.stream()
             .map(this::convertToDTO)
             .collect(Collectors.toList());
 }

 public RecipeDTO getRecipeById(Long id) {
     Recipe recipe = recipeRepository.findById(id)
             .orElseThrow(() -> new EntityNotFoundException("Recipe not found with id: " + id));
     return convertToDTO(recipe);
 }

 public RecipeDTO saveRecipe(RecipeDTO recipeDTO) {
     Recipe recipe = new Recipe();
     recipe.setName(recipeDTO.getName());
     recipe.setDescription(recipeDTO.getDescription());
     Recipe savedRecipe = recipeRepository.save(recipe);
     return convertToDTO(savedRecipe);
 }

 private RecipeDTO convertToDTO(Recipe recipe) {
     RecipeDTO recipeDTO = new RecipeDTO();
     recipeDTO.setId(recipe.getId());
     recipeDTO.setName(recipe.getName());
     recipeDTO.setDescription(recipe.getDescription());
     return recipeDTO;
 }
}

