package ru.bstrdn.voting.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bstrdn.voting.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestController {
    static final String REST_URL = "/rest/admin";

    @GetMapping("/restaurants")
    public List<Restaurant> getAll() {
        return null;
    }
}
