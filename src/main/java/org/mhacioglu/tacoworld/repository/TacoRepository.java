package org.mhacioglu.tacoworld.repository;

import org.mhacioglu.tacoworld.model.Taco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
    Page<Taco> findAll(Pageable pageable);
    Taco save(Taco taco);
}
