package com.mealmemory.repository;

import com.mealmemory.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Christian Jobst
 * created: 25.02.2023
 */
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
}
