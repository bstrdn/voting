package ru.bstrdn.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bstrdn.voting.model.Restaurant;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
