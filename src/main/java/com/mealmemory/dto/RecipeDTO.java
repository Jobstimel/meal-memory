package com.mealmemory.dto;

import java.util.List;

/**
 * @author Christian Jobst
 * created: 24.02.2023
 */
public record RecipeDTO(String recipeName, List<String> ingredients, String foundAt, String lastTimeCooked, int duration) { }
