package com.ystan.schedule.repositories;

import com.ystan.schedule.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String username);
}
