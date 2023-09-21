package sia.taco.repository;

import org.springframework.data.repository.CrudRepository;
import sia.taco.data.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
