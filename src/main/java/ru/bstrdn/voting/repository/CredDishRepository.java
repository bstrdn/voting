package ru.bstrdn.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bstrdn.voting.model.Dish;

public interface CredDishRepository extends JpaRepository<Dish, Integer> {
}
