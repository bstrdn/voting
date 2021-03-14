package ru.bstrdn.voting.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.bstrdn.voting.config.MyUserDetails;
import ru.bstrdn.voting.model.Restaurant;
import ru.bstrdn.voting.model.Vote;
import ru.bstrdn.voting.repository.CrudRestaurantRepository;
import ru.bstrdn.voting.service.UserService;
import ru.bstrdn.voting.util.exception.NotFoundException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserRestController {
    static final String REST_URL = "/rest/restaurant";

    @Autowired
    UserService userService;
    @Autowired
    CrudRestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (Objects.isNull(restaurant)) {
            throw new NotFoundException(id, "Restaurant not found");
        }
        log.info("getAll");
        return restaurant;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @PostMapping("/{id}")
    public Vote vote(@AuthenticationPrincipal MyUserDetails userDetails, @PathVariable int id) {
            return userService.toVote(id, userDetails);
    }


}