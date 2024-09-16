package org.mhacioglu.tacoworld.repository;

import java.util.List;

import org.mhacioglu.tacoworld.model.TacoOrder;
import org.mhacioglu.tacoworld.model.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(Users user, Pageable pageable);
    void deleteAll();




}
