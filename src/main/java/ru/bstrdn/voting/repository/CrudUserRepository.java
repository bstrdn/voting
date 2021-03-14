package ru.bstrdn.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bstrdn.voting.model.User;

import java.util.Optional;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

}
