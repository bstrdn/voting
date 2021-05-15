package ru.bstrdn.voting.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.bstrdn.voting.model.Dish;
import ru.bstrdn.voting.model.Restaurant;
import ru.bstrdn.voting.model.Vote;
import ru.bstrdn.voting.repository.CrudDishRepository;
import ru.bstrdn.voting.repository.CrudRestaurantRepository;
import ru.bstrdn.voting.security.UserDetailsImpl;
import ru.bstrdn.voting.service.VoteService;
import ru.bstrdn.voting.util.ValidList;
import ru.bstrdn.voting.util.exception.IncorrectDataException;
import ru.bstrdn.voting.util.exception.NotFoundException;

import javax.validation.Valid;
import java.awt.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantRestController {
    static final String REST_URL = "/rest/restaurants";

    @Autowired
    CrudDishRepository dishRepository;
    @Autowired
    CrudRestaurantRepository restaurantRepository;
    @Autowired
    VoteService voteService;

    @Cacheable(cacheNames = "restaurants")
    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantRepository.findAll();
    }

    @CacheEvict(cacheNames = "restaurant", allEntries = true)
    @PostMapping
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant, BindingResult result) {
        // TODO: 05.05.2021 refactoring log
        log.info("create");
        if (result.hasErrors()) {
            StringBuilder e = new StringBuilder();
            for (ObjectError err : result.getAllErrors()) {
                e.append(err.getDefaultMessage()).append(". ");
            }
            throw new NotFoundException(404, e.toString());
        }
        Restaurant created = restaurantRepository.save(restaurant);
//        List<Dish> dishList = restaurant.getDishes();
//        dishList.forEach(s -> s.setRestaurant(created));
//        dishRepository.saveAll(dishList);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Cacheable(cacheNames = "restaurant")
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Restaurant " + id + " not found"));
    }

    @PutMapping("/{id}")
    public Restaurant update(@PathVariable int id) {
    //todo realize
        return null;
    }


    @PostMapping("/{id}/vote")
    public Vote vote(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable int id) {
        log.info("voting");
        return voteService.toVote(id, userDetails.getUser());
    }

//    @PostMapping("/{id}/menu")
//    public List<Dish> setMenu(@Valid @RequestBody ValidList<Dish> menu, BindingResult result) {
//        log.info("set menu");
//
//        if (result.hasErrors()) {
//            StringBuilder e = new StringBuilder();
//            for (ObjectError err : result.getAllErrors()) {
//                e.append(err.getDefaultMessage()).append(". ");
//            }
//            throw new IncorrectDataException(e.toString());
//        }
//        try {
//            return dishRepository.saveAll(menu);
//        } catch (Exception e) {
//            throw new NotFoundException(404, "Restaurant not found");
//        }
//    }

@PostMapping("/{id}/menu")
    public List<Menu> getMenu(@PathVariable String id){

    return null;
}


}
