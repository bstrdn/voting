package ru.bstrdn.voting.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.bstrdn.voting.security.UserDetailsImpl;
import ru.bstrdn.voting.model.Restaurant;
import ru.bstrdn.voting.model.Vote;
import ru.bstrdn.voting.repository.CrudRestaurantRepository;
import ru.bstrdn.voting.service.VoteService;
import ru.bstrdn.voting.util.exception.NotFoundException;

import java.util.List;

//@RestController
//@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserRestController {
//    static final String REST_URL = "/rest/restaurant";

//    @Autowired
//    VoteService voteService;
//    @Autowired
//    CrudRestaurantRepository restaurantRepository;
//
//    @Cacheable(cacheNames = "restaurant")
//    @GetMapping("/{id}")
//    public Restaurant get(@PathVariable int id) {
//        log.info("get restaurant {}", id);
//        Restaurant restaurant = restaurantRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException(id, "Restaurant not found"));
//        return restaurant;
//    }
//
//    @Cacheable(cacheNames = "restaurants")
//    @GetMapping
//    public List<Restaurant> getAll() {
//        log.info("get all restaurants");
//        return restaurantRepository.findAll();
//    }
//
//    @PostMapping("/{id}")
//    public Vote vote(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable int id) {
//        log.info("voting");
//        return voteService.toVote(id, userDetails.getUser());
//    }
}
