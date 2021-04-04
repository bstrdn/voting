package ru.bstrdn.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bstrdn.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
