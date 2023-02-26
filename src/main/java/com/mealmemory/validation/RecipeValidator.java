package com.mealmemory.validation;

import com.mealmemory.dto.RecipeDTO;
import com.mealmemory.exception.APIPreconditionFailedException;
import org.springframework.stereotype.Component;

/**
 * @author Christian Jobst
 * created: 25.02.2023
 */
@Component
public class RecipeValidator {

    public void validateRecipe(RecipeDTO recipeDTO) throws APIPreconditionFailedException {
        if (recipeDTO.recipeName() == null || recipeDTO.recipeName().isEmpty()) {
            throw new APIPreconditionFailedException("recipe name was null or empty");
        }
        if (recipeDTO.ingredients() == null || recipeDTO.ingredients().isEmpty()) {
            throw new APIPreconditionFailedException("ingredients list was null or empty");
        }
        if (recipeDTO.lastTimeCooked() == null || recipeDTO.lastTimeCooked().isEmpty()) {
            throw new APIPreconditionFailedException("last cooking date for the recipe was null or empty");
        }
    }

}
