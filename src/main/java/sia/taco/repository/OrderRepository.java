package sia.taco.repository;

import sia.taco.data.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
