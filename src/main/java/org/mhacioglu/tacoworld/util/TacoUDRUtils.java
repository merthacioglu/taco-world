package org.mhacioglu.tacoworld.util;

import org.mhacioglu.tacoworld.model.Ingredient;
import org.mhacioglu.tacoworld.model.IngredientUDT;
import org.mhacioglu.tacoworld.model.Taco;
import org.mhacioglu.tacoworld.model.TacoUDT;

import java.util.List;
import java.util.stream.Collectors;

public class TacoUDRUtils {
    public static TacoUDT toTacoUDT(Taco taco) {
        return new TacoUDT(taco.getName(), taco.getIngredients());
    }

    public static List<IngredientUDT> toIngredientUDTs(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(TacoUDRUtils::toIngredientUDT)
                .collect(Collectors.toList());
    }

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }
}
