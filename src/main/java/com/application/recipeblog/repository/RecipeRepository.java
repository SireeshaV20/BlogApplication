package com.application.recipeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.recipeblog.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}