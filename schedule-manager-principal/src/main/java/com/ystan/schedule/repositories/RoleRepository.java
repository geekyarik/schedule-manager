package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);

    @Query(
            value = "SELECT R.* FROM SEC_ROLE R " +
                    "LEFT JOIN SEC_USERS_ROLES UR on UR.role_id = R.id " +
                    "LEFT JOIN SEC_USER U on U.id = UR.user_id " +
                    "WHERE U.email = ?1",
            nativeQuery = true)
    Set<Role> findRoleByUsername(String username);

    @Query(
            value = "INSERT INTO SEC_USERS_ROLES (user_id, role_id) " +
                    "VALUES (?1, ?2) " +
                    "RETURNING user_id",
            nativeQuery = true
    )
    String addRoleToUser(String userId, String roleId);

    @Modifying
    @Query(
            value = "DELETE FROM SEC_USERS_ROLES " +
                    "WHERE user_id = ?1 and role_id = ?2",
            nativeQuery = true
    )
    void removeRoleFromUser(String userId, String roleId);
}
