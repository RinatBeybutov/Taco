package sia.taco.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.taco.converter.TacoUDRUtils;
import sia.taco.data.Ingredient;
import sia.taco.data.Taco;
import sia.taco.data.TacoOrder;
import sia.taco.data.Type;
import sia.taco.repository.IngredientRepository;
import sia.taco.ui.TacoUi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
@Slf4j
@RequiredArgsConstructor
public class DesignController {

   private final IngredientRepository ingredientRepository;

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTako(@Valid TacoUi tacoUi, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if(errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(TacoUDRUtils.toTaco(tacoUi));
        log.info("Processing Tako {}", tacoUi);
        return "redirect:/orders/current";
    }


    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public TacoUi taco() {
        return new TacoUi();
    }
}
