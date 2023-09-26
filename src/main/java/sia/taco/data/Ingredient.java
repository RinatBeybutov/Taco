package sia.taco.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("ingredients")
@AllArgsConstructor
public final class Ingredient {

    @PrimaryKey
    private String id;
    private String name;
    private Type type;


}
