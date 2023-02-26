package com.mealmemory.api;

import com.mealmemory.dto.RecipeDTO;
import com.mealmemory.entity.RecipeEntity;
import com.mealmemory.exception.APIPreconditionFailedException;
import com.mealmemory.mysql.MySQLHandler;
import com.mealmemory.validation.RecipeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Christian Jobst
 * created: 24.02.2023
 */
@Slf4j
@RestController
public class MealMemoryApiV1 {

    private final MySQLHandler mySQLHandler;
    private final RecipeValidator recipeValidator;

    @Autowired
    public MealMemoryApiV1(MySQLHandler mySQLHandler, RecipeValidator recipeValidator) {
        this.mySQLHandler = mySQLHandler;
        this.recipeValidator = recipeValidator;
    }

    @PostMapping(value = "/api/v1/recipe/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<RecipeEntity> addNewRecipe(@RequestBody RecipeDTO recipeDTO) throws APIPreconditionFailedException {
        recipeValidator.validateRecipe(recipeDTO);
        RecipeEntity recipeEntity = mySQLHandler.addRecipeToDatabase(recipeDTO);
        return new ResponseEntity<>(recipeEntity, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/v1/recipe/delete/{id}")
    private ResponseEntity<String> deleteRecipe(@PathVariable long id) throws APIPreconditionFailedException {
        mySQLHandler.deleteRecipeById(id);
        return new ResponseEntity<>("Deleted recipe with id '" + id + "'", HttpStatus.OK);
    }

    @GetMapping(value = "api/v1/recipes/count/all")
    private ResponseEntity<Integer> countRecipes() {
        int numberOfSavedRecipes = mySQLHandler.countAllRecipes();
        return new ResponseEntity<>(numberOfSavedRecipes, HttpStatus.OK);
    }

    @GetMapping(value = "api/v1/recipes/find/all")
    private ResponseEntity<List<RecipeEntity>> findAllRecipes() {
        List<RecipeEntity> recipeEntityList = mySQLHandler.findAllRecipes();
        return new ResponseEntity<>(recipeEntityList, HttpStatus.OK);
    }

}
