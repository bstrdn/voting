package ru.bstrdn.voting.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.bstrdn.voting.model.Dish;
import ru.bstrdn.voting.model.Restaurant;
import ru.bstrdn.voting.repository.CrudDishRepository;
import ru.bstrdn.voting.repository.CrudRestaurantRepository;
import ru.bstrdn.voting.service.AdminService;
import ru.bstrdn.voting.util.ValidList;
import ru.bstrdn.voting.util.exception.NotFoundException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestController {
    static final String REST_URL = "/rest/restaurant/admin";

    @Autowired
    CrudDishRepository dishRepository;
    @Autowired
    CrudRestaurantRepository restaurantRepository;
    @Autowired
    AdminService adminService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
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
//    @ResponseStatus(HttpStatus.OK)
    public List<Dish> setMenu(@Valid @RequestBody ValidList<Dish> menu, BindingResult result) {

        if (result.hasErrors()) {
            throw new NotFoundException(0, result.getFieldError().getDefaultMessage());
        }
            return adminService.saveAll(menu);
    }
//
//    @PostMapping(value = "/setmenu", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public List<Dish> setMenu(@Valid @RequestBody List<Dish> menu, BindingResult result) {
//
//        if(result.hasErrors()) {
//            System.out.println("error");
//        } else {
//
//            List<Dish> list = adminService.saveAll(menu);
//            return list;
//        }
//return null;
//    }

}
