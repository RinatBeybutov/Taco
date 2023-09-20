package sia.taco.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sia.taco.data.Ingredient;
import sia.taco.data.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcIngredientRepository implements IngredientRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query("select id, name, type from Ingredient where id =?"
        , this::mapRowToIngredient,
                id);
        return results.isEmpty() ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.id(),
                ingredient.name(),
                ingredient.type().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Type.valueOf(row.getString("type"))
        );
    }
}
