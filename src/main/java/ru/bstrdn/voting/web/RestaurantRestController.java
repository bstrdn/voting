package ru.bstrdn.voting.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.bstrdn.voting.model.Dish;
import ru.bstrdn.voting.model.Restaurant;
import ru.bstrdn.voting.model.Vote;
import ru.bstrdn.voting.repository.CrudDishRepository;
import ru.bstrdn.voting.repository.CrudRestaurantRepository;
import ru.bstrdn.voting.security.UserDetailsImpl;
import ru.bstrdn.voting.service.VoteService;
import ru.bstrdn.voting.util.exception.IllegalRequestDataException;
import ru.bstrdn.voting.util.exception.IncorrectDataException;
import ru.bstrdn.voting.util.exception.NotFoundException;

import javax.validation.Valid;
import java.util.List;

import static ru.bstrdn.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantRestController {
    static final String REST_URL = "/rest/restaurants";

    private final CrudDishRepository dishRepository;
    private final CrudRestaurantRepository restaurantRepository;
    private final VoteService voteService;

    public RestaurantRestController(CrudDishRepository dishRepository, CrudRestaurantRepository restaurantRepository, VoteService voteService) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
        this.voteService = voteService;
    }

    @Cacheable(cacheNames = "restaurants")
    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(cacheNames = "restaurant", allEntries = true)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant create(@Valid @RequestBody Restaurant restaurant, BindingResult result) {
        log.info("create new restaurant, id {}");
        checkNew(restaurant);
        if (result.hasErrors()) {
            StringBuilder e = new StringBuilder();
            for (ObjectError err : result.getAllErrors()) {
                e.append(err.getDefaultMessage()).append(". ");
            }
            throw new IncorrectDataException(e.toString());
        }
        return restaurantRepository.save(restaurant);
    }

    @Cacheable(cacheNames = "restaurant")
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Restaurant " + id + " not found"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Restaurant update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update restaurant №{}", id);
        checkRestarautExistsById(id);
        if (restaurant.isNew()) {
            restaurant.setId(id);
        } else if (!restaurant.getId().equals(id)) {
            throw new IncorrectDataException("request id do not match");
        }
        return restaurantRepository.save(restaurant);
    }

    @PostMapping("/{id}/vote")
    public Vote vote(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable int id) {
        log.info("vote for restaurant №{}", id);
        return voteService.toVote(id, userDetails.getUser());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(cacheNames = "menu", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/menu")
    public Dish createDish(@PathVariable int id, @Valid @RequestBody Dish dish, BindingResult result) {
        log.info("add dish to menu for restaurant №{}", id);
        checkNew(dish);
        checkRestarautExistsById(id);
        checkValidError(result);
        dish.setRestaurant(new Restaurant(id));
        return dishRepository.save(dish);
    }

    @Cacheable(cacheNames = "menu")
    @GetMapping("/{id}/menu")
    public List<Dish> getMenu(@PathVariable Integer id) {
        log.info("get menu for restaurant №{}", id);
        checkRestarautExistsById(id);
        return dishRepository.findByRestaurantId(id);
    }

    @CacheEvict(cacheNames = "menu")
    @PutMapping("/{id}/menu/{dishId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Dish updateDish(@PathVariable Integer id, @PathVariable Integer dishId, @Valid @RequestBody Dish dish, BindingResult result) {
        checkValidError(result);
        if (dish.isNew()) {
            dish.setId(dishId);
        } else if (!dish.getId().equals(dishId)) {
            throw new IncorrectDataException("request id do not match");
        }
        dish.setRestaurant(new Restaurant(id));
        return dishRepository.save(dish);
    }


    protected void checkRestarautExistsById(Integer id) {
        if (!restaurantRepository.existsById(id)) {
            throw new IllegalRequestDataException("restaurant id " + id + " not exist");
        }
    }

    protected void checkValidError(BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder e = new StringBuilder();
            for (ObjectError err : result.getAllErrors()) {
                e.append(err.getDefaultMessage()).append(". ");
            }
            throw new IncorrectDataException(e.toString());
        }
    }
}
