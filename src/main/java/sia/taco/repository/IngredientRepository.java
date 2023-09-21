package sia.taco.repository;

import org.springframework.data.repository.CrudRepository;
import sia.taco.data.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
