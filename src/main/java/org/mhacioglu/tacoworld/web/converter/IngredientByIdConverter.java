package org.mhacioglu.tacoworld.web.converter;

import lombok.NonNull;
import org.mhacioglu.tacoworld.web.model.Ingredient;
import org.mhacioglu.tacoworld.web.model.Ingredient.Type;
import org.mhacioglu.tacoworld.web.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(@NonNull  String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
