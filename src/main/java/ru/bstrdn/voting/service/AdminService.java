package ru.bstrdn.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bstrdn.voting.model.Dish;
import ru.bstrdn.voting.repository.CrudDishRepository;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    CrudDishRepository dishRepository;

    public List<Dish> saveAll(List<Dish> menu) {
        List<Dish> list = dishRepository.saveAll(menu);

        return list;
    }
}
