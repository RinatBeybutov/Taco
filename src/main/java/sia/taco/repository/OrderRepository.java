package sia.taco.repository;

import org.springframework.data.repository.CrudRepository;
import sia.taco.data.TacoOrder;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {
}
