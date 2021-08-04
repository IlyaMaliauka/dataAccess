package com.epam.training.repository;

import com.epam.training.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByName(String name);
}
