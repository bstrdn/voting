package ru.bstrdn.voting.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.bstrdn.voting.model.Dish;
import ru.bstrdn.voting.model.Restaurant;
import ru.bstrdn.voting.repository.CrudDishRepository;
import ru.bstrdn.voting.repository.CrudRestaurantRepository;
import ru.bstrdn.voting.util.ValidList;
import ru.bstrdn.voting.util.exception.IncorrectDataException;
import ru.bstrdn.voting.util.exception.NotFoundException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestController {
    static final String REST_URL = "/rest/restaurant/admin";

    @Autowired
    CrudDishRepository dishRepository;
    @Autowired
    CrudRestaurantRepository restaurantRepository;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant, BindingResult result) {
        if (result.hasErrors()) {
            throw new NotFoundException(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
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
    @ResponseStatus(HttpStatus.CREATED)
    public List<Dish> setMenu(@Valid @RequestBody ValidList<Dish> menu, BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder e = new StringBuilder();
            for (ObjectError err : result.getAllErrors()) {
                e.append(err.getDefaultMessage() + ". ");
            }
            throw new IncorrectDataException(e.toString());
        }
        try {
            List<Dish> dishList = dishRepository.saveAll(menu);
            return dishList;
        } catch (Exception e) {
            throw new NotFoundException(404, "Ресторан не найден");
        }
    }
}
