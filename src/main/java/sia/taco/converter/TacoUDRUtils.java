package sia.taco.converter;

import sia.taco.data.Ingredient;
import sia.taco.data.IngredientUDT;
import sia.taco.data.Taco;
import sia.taco.data.TacoUDT;
import sia.taco.ui.TacoUi;

import java.util.stream.Collectors;

public class TacoUDRUtils {
    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

    public static TacoUDT toTacoUDT(Taco taco) {
        return new TacoUDT(taco.getName(), taco.getIngredients());
    }

    public static Taco toTaco(TacoUi tacoUi) {
        Taco taco = new Taco();
        taco.setCreatedAt(tacoUi.getCreatedAt());
        taco.setIngredients(tacoUi.getIngredients().stream().map(TacoUDRUtils::toIngredientUDT).collect(Collectors.toList()));
        taco.setName(tacoUi.getName());
        return taco;
    }
}
