package org.mhacioglu.tacoworld.repository;


import org.mhacioglu.tacoworld.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
