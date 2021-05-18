package com.ystan.schedule.services;

import com.ystan.schedule.models.Role;
import com.ystan.schedule.repositories.RoleRepository;
import com.ystan.schedule.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public Set<Role> getUserRoles(String username) {
        return roleRepository.findRoleByUsername(username);
    }

    public void editUsersRole(String username, String roleId, boolean enable) {
        final String userId = userRepository.findByEmail(username).getId();

        if (enable) {
            roleRepository.addRoleToUser(userId, roleId);
        } else {
            roleRepository.removeRoleFromUser(userId, roleId);
        }
    }
}
