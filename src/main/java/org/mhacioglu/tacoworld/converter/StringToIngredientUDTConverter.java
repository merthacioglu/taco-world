package org.mhacioglu.tacoworld.converter;

import org.mhacioglu.tacoworld.model.Ingredient;
import org.mhacioglu.tacoworld.model.Ingredient.Type;
import org.mhacioglu.tacoworld.model.IngredientUDT;
import org.mhacioglu.tacoworld.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringToIngredientUDTConverter implements Converter<String, IngredientUDT> {
    private final IngredientRepository ingredientRepository;

    public StringToIngredientUDTConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String source) {
        // Fetch the ingredient by its ID
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(source);

        if (!ingredientOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid ingredient ID: " + source);
        }

        Ingredient ingredient = ingredientOptional.get();
        // Create and return the corresponding IngredientUDT
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }
}
