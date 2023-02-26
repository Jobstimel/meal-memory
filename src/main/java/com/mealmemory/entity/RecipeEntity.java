package com.mealmemory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * @author Christian Jobst
 * created: 25.02.2023
 */
@Data
@Entity
@Table(name = "recipes")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipeName;

    private List<String> ingredients;

    private String foundAt;

    private String lastTimeCooked;
    private int duration;

}
