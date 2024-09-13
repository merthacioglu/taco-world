package org.mhacioglu.tacoworld.converter;

import lombok.NonNull;
import org.mhacioglu.tacoworld.model.Ingredient;
import org.mhacioglu.tacoworld.model.Ingredient.Type;
import org.mhacioglu.tacoworld.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(@NonNull  String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
