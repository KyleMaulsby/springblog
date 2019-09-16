package com.codeup.springblog.repo;

import com.codeup.springblog.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
