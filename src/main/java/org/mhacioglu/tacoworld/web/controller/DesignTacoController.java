package org.mhacioglu.tacoworld.web.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.mhacioglu.tacoworld.web.model.Ingredient;
import org.mhacioglu.tacoworld.web.model.Ingredient.Type;
import org.mhacioglu.tacoworld.web.model.Taco;
import org.mhacioglu.tacoworld.web.model.TacoOrder;
import org.mhacioglu.tacoworld.web.repository.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredients.forEach(ingredientList::add);
        Type[] types = Type.values();
        for (Type type :  types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredientList, type));
        }

    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid  Taco taco,
                              Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }



}
