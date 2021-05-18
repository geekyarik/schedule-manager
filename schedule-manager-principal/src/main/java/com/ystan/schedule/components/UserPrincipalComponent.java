package com.ystan.schedule.components;

import com.ystan.schedule.models.Role;
import com.ystan.schedule.models.User;
import com.ystan.schedule.security.AuthRequest;
import com.ystan.schedule.security.AuthResponse;
import com.ystan.schedule.security.JSONWebTokenUtils;
import com.ystan.schedule.security.RegistrationRequest;
import com.ystan.schedule.services.RoleService;
import com.ystan.schedule.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Component
@RestController
public class UserPrincipalComponent {

    private final AuthenticationManager authenticationManager;
    private final UserService userDetailsService;
    private final JSONWebTokenUtils jsonWebTokenUtils;
    private final RoleService roleService;

    @Autowired
    public UserPrincipalComponent(AuthenticationManager authenticationManager, UserService userDetailsService, JSONWebTokenUtils jsonWebTokenUtils, RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jsonWebTokenUtils = jsonWebTokenUtils;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jsonWebTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) throws Exception {
        try {
            userDetailsService.saveUser(User.fromRegistrationRequest(registrationRequest));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(registrationRequest.getEmail(), registrationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("User with email already exists");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(registrationRequest.getEmail());
        final String jwt = jsonWebTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @RequestMapping(value = "/principal", method = RequestMethod.GET)
    public ResponseEntity<?> principal() throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUsername;

        if (principal instanceof UserDetails) {
            currentUsername = ((UserDetails) principal).getUsername();
        } else {
            currentUsername = principal.toString();
        }

        Set<Role> roles = roleService.getUserRoles(currentUsername);

        return ResponseEntity.ok(roles);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers() throws Exception {
        List<User> users = userDetailsService.allUsers();

        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public ResponseEntity<?> editRole(String username, String roleId, boolean enable) throws Exception {
        roleService.editUsersRole(username, roleId, enable);

        return ResponseEntity.ok(username);
    }
}
