package com.mealmemory.mysql;

import com.mealmemory.dto.RecipeDTO;
import com.mealmemory.entity.RecipeEntity;
import com.mealmemory.exception.APIPreconditionFailedException;
import com.mealmemory.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Christian Jobst
 * created: 25.02.2023
 */
@Slf4j
@Component
public class MySQLHandler {

    private final RecipeRepository recipeRepository;

    @Autowired
    public MySQLHandler(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeEntity addRecipeToDatabase(RecipeDTO recipeDTO) throws APIPreconditionFailedException {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeName(recipeDTO.recipeName());
        recipeEntity.setIngredients(recipeDTO.ingredients());
        recipeEntity.setFoundAt(recipeDTO.foundAt());
        recipeEntity.setLastTimeCooked(recipeDTO.lastTimeCooked());
        recipeEntity.setDuration(recipeDTO.duration());
        for (RecipeEntity savedRecipe : findAllRecipes()) {
            if (recipeEntity.equals(savedRecipe)) {
                logAndThrow(String.format("Recipe with name '%s' already in database", recipeEntity.getRecipeName()));
            }
        }
        recipeRepository.save(recipeEntity);
        log.info("Added recipe '{}' to database", recipeEntity.getRecipeName());
        return recipeEntity;
    }

    public void deleteRecipeById(long id) throws APIPreconditionFailedException {
        if (recipeRepository.findById(id).isEmpty()) {
            logAndThrow(String.format("No database entry with id '%s' was found", id));
        }
        recipeRepository.deleteById(id);
        log.info("Deleted recipe with id '{}' from database", id);
    }

    public int countAllRecipes() {
        return recipeRepository.findAll().size();
    }

    public List<RecipeEntity> findAllRecipes() {
        return recipeRepository.findAll();
    }

    private void logAndThrow(String errorMessage) throws APIPreconditionFailedException {
        log.warn(errorMessage);
        throw new APIPreconditionFailedException(errorMessage);
    }

}
