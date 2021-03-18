package ru.bstrdn.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.bstrdn.voting.model.Dish;
import ru.bstrdn.voting.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT v from Vote v WHERE v.user.id=:userId AND v.voted >= current_date")
    Vote getTodayVote(@Param("userId") int userId);
}
