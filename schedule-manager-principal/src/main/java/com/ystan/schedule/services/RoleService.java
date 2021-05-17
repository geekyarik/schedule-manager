package com.ystan.schedule.services;

import com.ystan.schedule.models.Role;
import com.ystan.schedule.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Set<Role> getUserRoles(String username) {
        return roleRepository.findRoleByUsername(username);
    }
}
