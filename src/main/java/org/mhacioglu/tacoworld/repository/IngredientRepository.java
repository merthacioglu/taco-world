package org.mhacioglu.tacoworld.repository;

import org.mhacioglu.tacoworld.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
