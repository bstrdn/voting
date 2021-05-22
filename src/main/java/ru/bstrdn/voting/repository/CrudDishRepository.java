package ru.bstrdn.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bstrdn.voting.model.Dish;

import java.util.List;

public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findByRestaurantId(Integer id);
}
