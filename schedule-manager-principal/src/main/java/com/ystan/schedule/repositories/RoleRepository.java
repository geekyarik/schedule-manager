package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoleRepository extends JpaRepository<Role, String> {

}
