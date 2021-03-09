package ru.bstrdn.voting.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.bstrdn.voting.model.Dish;
import ru.bstrdn.voting.model.Restaurant;
import ru.bstrdn.voting.repository.CredDishRepository;
import ru.bstrdn.voting.repository.CrudRestaurantRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestController {
    static final String REST_URL = "/rest/admin/restaurant";

    @Autowired
    CrudRestaurantRepository restaurantRepository;
    @Autowired
    CredDishRepository dishRepository;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantRepository.save(restaurant);
        List<Dish> dishList = restaurant.getDishes();
        dishList.forEach(s -> s.setRestaurant(created));
        dishRepository.saveAll(dishList);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/setmenu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Restaurant> setMenu(@RequestBody List<Dish> menu) {
        dishRepository.saveAll(menu);
        return null;
    }

}
