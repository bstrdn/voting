package ru.bstrdn.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bstrdn.voting.config.MyUserDetails;
import ru.bstrdn.voting.model.Restaurant;
import ru.bstrdn.voting.model.User;
import ru.bstrdn.voting.model.Vote;
import ru.bstrdn.voting.repository.CrudRestaurantRepository;
import ru.bstrdn.voting.repository.CrudVoteRepository;
import ru.bstrdn.voting.util.exception.NotFoundException;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    CrudVoteRepository voteRepository;
    @Autowired
    CrudRestaurantRepository restaurantRepository;

    public Vote toVote(int id, MyUserDetails userDetails) {

        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (Objects.isNull(restaurant)) {
            throw new NotFoundException(id, "Restaurant not found");
        }

        LocalTime localTime = LocalTime.now();

        User user = userDetails.getUser();
        Vote todayVote = voteRepository.getTodayVote(user.getId());

        if (Objects.isNull(todayVote)) {
            return voteRepository.save(new Vote(user, restaurant));
        } else if (localTime.toSecondOfDay() < 39600) {
            todayVote.setRestaurant(restaurant);
            todayVote.setVoted(new Date());
            return voteRepository.save(todayVote);
        }
        throw new NotFoundException(id, "Already voted");
    }
}
