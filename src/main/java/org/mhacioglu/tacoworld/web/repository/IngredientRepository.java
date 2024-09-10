package org.mhacioglu.tacoworld.web.repository;

import org.mhacioglu.tacoworld.web.model.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
