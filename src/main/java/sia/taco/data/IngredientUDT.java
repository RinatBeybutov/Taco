package sia.taco.data;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@UserDefinedType("ingredient")
public class IngredientUDT {
    private final String name;
    private final Type type;
}
