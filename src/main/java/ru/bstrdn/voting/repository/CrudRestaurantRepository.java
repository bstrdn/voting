package ru.bstrdn.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bstrdn.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

//    @Query("SELECT r.id, r.name from Restaurant r")
//    List<Restaurant> getAllWithMenuDay(LocalDate date);
    Restaurant getWithoutDishes(int id);
}
