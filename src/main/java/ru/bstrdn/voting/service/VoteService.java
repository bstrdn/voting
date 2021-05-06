package ru.bstrdn.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bstrdn.voting.model.Restaurant;
import ru.bstrdn.voting.model.User;
import ru.bstrdn.voting.model.Vote;
import ru.bstrdn.voting.repository.CrudRestaurantRepository;
import ru.bstrdn.voting.repository.CrudVoteRepository;
import ru.bstrdn.voting.util.exception.IncorrectDataException;
import ru.bstrdn.voting.util.exception.NotFoundException;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Service
public class VoteService {
    private static final int BORDER_TIME = 39600;

    @Autowired
    private CrudVoteRepository voteRepository;
    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Transactional
    public Vote toVote(int id, User user) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Restaurant not found"));

        LocalTime localTime = LocalTime.now();
        Vote todayVote = voteRepository.getTodayVote(user.getId());

        if (Objects.isNull(todayVote)) {
            return voteRepository.save(new Vote(user, restaurant));
        } else {
            if (localTime.toSecondOfDay() < BORDER_TIME) {
                todayVote.setRestaurant(restaurant);
                todayVote.setVoted(new Date());
                return voteRepository.save(todayVote);
            }
        }
        throw new IncorrectDataException("Already voted");
    }
}
