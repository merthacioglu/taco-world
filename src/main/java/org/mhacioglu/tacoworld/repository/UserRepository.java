package org.mhacioglu.tacoworld.repository;

import org.mhacioglu.tacoworld.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
