package org.mhacioglu.tacoworld.repository;

import org.mhacioglu.tacoworld.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
